package com.clear.param;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName Response
 *
 * @author qml
 * Date 2020/8/13 16:25
 * Version 1.0
 **/
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 3403459610630116268L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;
    public static final int NO_AUTHORIZATION_CODE = 100;

    public static final Response<String> SUCCESS = new Response<>(null);
    public static final Response<String> FAIL = new Response<>(FAIL_CODE, null);
    public static final Response<String> NO_AUTHORIZATION = new Response<String>(NO_AUTHORIZATION_CODE, "未登录");

    private int code;
    private String message;
    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }
}