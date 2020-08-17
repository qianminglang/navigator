package com.clear.repository.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clear.entity.UserInfoDto;
import com.clear.mapper.UserMapper;
import com.clear.param.input.UserInfoParam;
import com.clear.repository.UserInfoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName UserInfoRepositoryImpl
 *
 * @author qml
 * Date 2020/8/14 15:32
 * Version 1.0
 **/
@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserInfoDto> selectUserInfo(UserInfoDto userInfoDto) {
        return userMapper.selectUserInfo(userInfoDto);
    }

    @Override
    public IPage<UserInfoDto> selectUserInfoByPage(Page<UserInfoDto> userInfoDtoPage, UserInfoParam userInfoParam) {
        return userMapper.selectUserInfoByPage(userInfoDtoPage,userInfoParam);
    }
}