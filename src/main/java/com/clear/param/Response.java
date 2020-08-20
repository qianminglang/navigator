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
public class Response<T> extends TimeOutPut implements Serializable {

    private static final long serialVersionUID = 3403459610630116268L;

    public static final boolean SUCCESS_STATUS = true;
    public static final boolean FAIL_STATUS = false;
    public static final int NO_AUTHORIZATION_CODE = 100;


    public static <T> Response<T> SUCCESS(T data) {
        Response<T> resp = new Response<>();
        resp.success = SUCCESS_STATUS;
        resp.data = data;
        return resp;
    }

    public static <T> Response<T> FAIL(T data) {
        Response<T> resp = new Response<>();
        resp.success = FAIL_STATUS;
        resp.data = data;
        return resp;
    }

    private boolean success;
    private String message;
    private T data;

    public Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(T data) {
        this.success = FAIL_STATUS;
        this.data = data;
    }

    public Response() {
    }
}