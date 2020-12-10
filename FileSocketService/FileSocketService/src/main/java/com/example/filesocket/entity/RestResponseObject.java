package com.example.filesocket.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description base 数据类
 **/
@Getter
@Setter
public class RestResponseObject implements Serializable {

    /**
     * 代码 200为成功
     */
    private int code;

    /**
     * 成功或错误信息
     */
    private String msg;

    /**
     * 返回的对象
     */
    private Object data;

    public RestResponseObject() {
    }

    public RestResponseObject(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
