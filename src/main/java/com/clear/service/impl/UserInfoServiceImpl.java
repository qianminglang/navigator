package com.clear.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clear.entity.UserInfoDto;
import com.clear.param.input.UserInfoParam;
import com.clear.mapper.UserMapper;
import com.clear.repository.UserInfoRepository;
import com.clear.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName UserInfoServiceImpl
 *
 * @author qml
 * Date 2020/8/13 10:01
 * Version 1.0
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfoDto> selectUserInfo(UserInfoParam userInfoParam) {
        List<UserInfoDto> userInfoDtoIPage = userInfoRepository.selectUserInfo(userInfoParam);
        return userInfoDtoIPage;
    }

    @Override
    public IPage<UserInfoDto> selectUserInfoByPage(UserInfoParam userInfoParam) {
        Page<UserInfoDto> userInfoDtoPage = new Page<>(1, 3);
        return userInfoRepository.selectUserInfoByPage(userInfoDtoPage,userInfoParam);
    }
}