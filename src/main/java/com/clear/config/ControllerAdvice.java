package com.clear.config;

import com.clear.exception.ClearArgumentException;
import com.clear.param.Response;
import lombok.extern.slf4j.Slf4j;
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
    @ExceptionHandler(value = ClearArgumentException.class)
    @ResponseBody
    public Response<String> exceptionHandler(ClearArgumentException e) {
        log.error("发生异常：{}",e.getMessage());
        return new Response(false, e.getMessage());
    }
}