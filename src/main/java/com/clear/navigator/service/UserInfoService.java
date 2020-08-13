package com.clear.navigator.service;

import com.clear.navigator.dto.UserInfoDto;
import com.clear.navigator.param.input.UserInfoParam;

import java.util.List;

/**
 * ClassName UserInfoService
 *
 * @author qml
 * Date 2020/8/13 9:44
 * Version 1.0
 **/

public interface UserInfoService {
    /**
     * 查询用户信息
     *
     * @param
     * @return com.clear.navigator.domain.UserInfo
     * @author 3Clear1
     * @param userInfoParam 用户信息入参
     * @date 2020/8/13 10:01
     **/
    List<UserInfoDto> selectUserInfo(UserInfoParam userInfoParam);
}