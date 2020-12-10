package com.example.filesocket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "fileName")//指定表名
public class FileNameBean {

    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private String id;

    @TableField(value = "uid")
    private String uid;

    @TableField(value = "fileName")
    private String fileName;

    @TableId()
    private String path;

}
