package com.clear.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clear.entity.UserInfoDto;
import com.clear.param.input.UserInfoParam;

import java.util.List;

/**
 * ClassName UserInfoRepository
 *
 * @author qml
 * Date 2020/8/14 15:31
 * Version 1.0
 **/

public interface UserInfoRepository {
    List<UserInfoDto> selectUserInfo(UserInfoParam userInfoParam);

    IPage<UserInfoDto> selectUserInfoByPage(Page<UserInfoDto> userInfoDtoPage, UserInfoParam userInfoParam);
}