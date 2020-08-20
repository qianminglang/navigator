package com.clear.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName TimeAspect
 *
 * @author qml
 * Date 2020/8/20 17:15
 * Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Pointcut("execution(public * com.clear.controller.*.*(..))")
    public void log() {
    }

    /**
     * 统计请求的处理时间
     *
     * @author 3Clear1
     * @date 2020/8/20 17:33
     * @param null
     * @return
     **/
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("log()")
    public void doBefore() {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("调用路径:,{}", request.getRequestURL().toString());
    }

    @AfterReturning(pointcut = "log()")
    public void doAfterReturning() {
        log.info("方法调用时间:{}", (System.currentTimeMillis() - startTime.get()) + "ms");
    }

}
