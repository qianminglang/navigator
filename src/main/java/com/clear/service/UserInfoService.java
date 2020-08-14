package com.clear.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.clear.entity.UserInfoDto;
import com.clear.param.input.UserInfoParam;

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

    /**
     * 分页查询
     * @author 3Clear1
     * @date 2020/8/13 15:58
      * @param userInfoParam
     * @return java.util.List<com.clear.navigator.dto.UserInfoDto>
     **/
    IPage<UserInfoDto> selectUserInfoByPage(UserInfoParam userInfoParam);
}