package com.example.filesocket.controller;

import com.example.filesocket.entity.FileNameBean;
import com.example.filesocket.mapper.UploadFileDao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UploadController
 **/

@Component
public class UploadController implements CommandLineRunner {

    @Autowired
    public UploadFileDao uploadFileDao;

    @Autowired
    public Gson gson;


    @Override
    public void run(String... args) throws Exception {
        try {

            ServerSocket server = new ServerSocket(9097);
            System.out.println("服务端接收线程启动了, 监听端口:9097");
            ThreadPoolExecutor pool = new ThreadPoolExecutor(
                    10, 30,20,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10),
                    new ThreadPoolExecutor.DiscardOldestPolicy()
            );

            while (true) {
                System.out.println("循环获取客户端是否有发送文件。。。");
                Socket  socket = server.accept();
                ClientThread command = new ClientThread(socket);
                pool.execute(command);
            }
        }catch (Exception e){

        }
    }


    class ClientThread implements Runnable {
        private Socket socket;

        public ClientThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("与客户端建立连接！");
                DataInputStream is = new DataInputStream(socket.getInputStream());
                File path = new File(ResourceUtils.getURL("").getPath());
                if (!path.exists()) {
                    path = new File("");
                }

                File images = new File(path.getAbsolutePath(), "src/main/webapp/images/");
                if (!images.exists()) {
                    images.mkdirs();
                }

                String fileName = is.readUTF();
                String pathName = images + "/" + fileName;
                System.out.println("新生成的文件名为:" + pathName);
                FileOutputStream fos = new FileOutputStream(pathName);
                byte[] b = new byte[1024*4];
                int length = 0;
                while ((length = is.read(b)) != -1) {
                    fos.write(b, 0, length);
                }
                fos.flush();
                fos.close();
                is.close();
                socket.close();

                FileNameBean fileNameBean = new FileNameBean();
                fileNameBean.setFileName(fileName);
                fileNameBean.setUid("2");
                fileNameBean.setPath("images/"+fileName);
                uploadFileDao.insert(fileNameBean);
                System.out.println("文件接收完成");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件接收失败");
            }
        }
    }

}
