package com.clear.config;

import com.alibaba.fastjson.JSONObject;
import com.clear.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName TokenInterceptor
 *
 * @author qml
 * Date 2020/8/17 10:40
 * Version 1.0
 **/

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token!=null){
            boolean result= TokenUtil.verify(token);
            if (result){
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json=new JSONObject();
            json.put("msg","token verify fail");
            json.put("code",401);
            response.getWriter().append(json.toString());
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}