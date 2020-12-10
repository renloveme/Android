package com.example.filesocket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "user")//指定表名
public class User {

    @TableId(value = "uid",type = IdType.AUTO)//指定自增策略
    private String uid;

    @TableField(value = "userName")
    public String userName;

    @TableField(value = "passWord")
    public String passWord;

}
