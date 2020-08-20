package com.clear.config;

import com.clear.param.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static cn.hutool.core.date.DateTime.now;

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

    @Around("log()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Response response = (Response) pjp.proceed();
        //改变返回值
        response.setStartTime(new Date(startTime.get()));
        response.setEndTime(now());
        response.setDuration((System.currentTimeMillis() - startTime.get()) + "ms");
        return response;
    }


    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) {
        log.info("方法调用时间:{}", (System.currentTimeMillis() - startTime.get()) + "ms");
    }

}
