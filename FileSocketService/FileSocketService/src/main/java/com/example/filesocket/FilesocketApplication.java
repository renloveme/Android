package com.example.filesocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.filesocket")
public class FilesocketApplication {

    public static void main(String[] args) {
        System.out.println("FilesocketApplication启动了---");
        SpringApplication.run(FilesocketApplication.class, args);
    }

}
