package com.clear.config;

import com.clear.exception.ClearArgumentException;
import com.clear.param.Response;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName ControllerAdvice
 * 异常处理信息类
 *
 * @author qml
 * Date 2020/8/14 10:59
 * Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    /**
     * 捕获所有异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<String> handleException(Exception e) {
        log.error("异常:", e);
        return new Response(false, e.getMessage());
    }

    /**
     * 主动V抛出的异常
     *
     * @param e
     * @return com.clear.param.Response<java.lang.String>
     * @author 3Clear1
     * @date 2020/8/23 13:26
     **/
    @ExceptionHandler(value = ClearArgumentException.class)
    @ResponseBody
    public Response<String> exceptionHandler(ClearArgumentException e) {
        log.error("发生异常：{}", e.getMessage());
        return new Response(false, e.getMessage());
    }

    /**
     * 操作数据或库出现异常
     */
    @ExceptionHandler(value = PSQLException.class)
    @ResponseBody
    public Response<String> handleException(PSQLException e) {
        log.error("操作数据库出现异常:", e);
        return new Response(false, e.getMessage());
    }

}