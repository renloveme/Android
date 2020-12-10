package com.example.filesocket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.filesocket.entity.FileNameBean;
import com.example.filesocket.entity.RestResponseObject;
import com.example.filesocket.entity.User;
import com.example.filesocket.mapper.UploadFileDao;
import com.example.filesocket.mapper.UserDao;
import com.example.filesocket.utils.Constant;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Slf4j
@Controller
public class UserController {

    @Autowired
    public UserDao userDao;

    @Autowired
    public UploadFileDao uploadFileDao;

    @Autowired
    public Gson gson;


    @RequestMapping(value = "user/register", method = RequestMethod.GET)
    @ResponseBody
    public String userRegister(@Validated User user) {
        RestResponseObject restResponseObject = new RestResponseObject();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", user.getUserName());
        User isExitsUser = userDao.selectOne(wrapper);
        if (isExitsUser == null) {
            userDao.insert(user);
            restResponseObject.setCode(Constant.OK);
            restResponseObject.setMsg("用户注册成功");
        } else {
            restResponseObject.setCode(Constant.NO_OK);
            restResponseObject.setMsg("用户已经注册");
        }
        restResponseObject.setData(new Object());
        return gson.toJson(restResponseObject);
    }


    @RequestMapping(value = "user/login", method = RequestMethod.GET)
    @ResponseBody
    public String appUserLogin(@Validated User user) {
        RestResponseObject restResponseObject = new RestResponseObject();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", user.getUserName());
        wrapper.eq("passWord", user.getPassWord());
        User isExitsUser = userDao.selectOne(wrapper);
        if (isExitsUser != null) {
            restResponseObject.setCode(Constant.OK);
            restResponseObject.setMsg("登录成功");
            restResponseObject.setData(isExitsUser);
        } else {
            restResponseObject.setCode(Constant.NO_OK);
            restResponseObject.setMsg("用户名或者密码错误");
            restResponseObject.setData(new Object());
        }
        return gson.toJson(restResponseObject);
    }


    @RequestMapping(value = "file/getFileList", method = RequestMethod.GET)
    @ResponseBody
    public String getFileList() {
        RestResponseObject restResponseObject = new RestResponseObject();

        List<FileNameBean> fileNameBeans = uploadFileDao.selectList(null);
        if (fileNameBeans != null && fileNameBeans.size() > 0) {
            restResponseObject.setCode(Constant.OK);
            restResponseObject.setMsg("查询成功");
            restResponseObject.setData(fileNameBeans);
        } else {
            restResponseObject.setCode(Constant.NO_OK);
            restResponseObject.setMsg("暂无上传文件");
            restResponseObject.setData(fileNameBeans);
        }
        return gson.toJson(restResponseObject);
    }


    @RequestMapping(value = "file/deleteFile", method = RequestMethod.GET)
    @ResponseBody
    public String deleteFile(String id) {
        RestResponseObject restResponseObject = new RestResponseObject();

        uploadFileDao.deleteById(id);
        restResponseObject.setCode(Constant.OK);
        restResponseObject.setMsg("删除成功");
        restResponseObject.setData(new Object());

        return gson.toJson(restResponseObject);
    }

}
