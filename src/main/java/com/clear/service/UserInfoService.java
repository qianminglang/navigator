package com.clear.service;

import com.clear.param.input.LoginParam;

import java.util.Map;

/**
 * ClassName UserInfoService
 *
 * @author qml
 * Date 2020/8/13 9:44
 * Version 1.0
 **/

public interface UserInfoService {
    /**
     * 用户登录，当登录成功得到token，
     * 不成功则返回对应得消息
     *
     * @param
     * @return com.clear.navigator.domain.UserInfo
     * @author 3Clear1
     * @param loginParam 用户登录
     * @date 2020/8/13 10:01
     **/
    Map<String,String> login(LoginParam loginParam);
}