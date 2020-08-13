package com.clear.navigator.service.impl;

import com.clear.navigator.dto.UserInfoDto;
import com.clear.navigator.param.input.UserInfoParam;
import com.clear.navigator.repository.UserRepository;
import com.clear.navigator.service.UserInfoService;
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
    private UserRepository userRepository;

    @Override
    public List<UserInfoDto> selectUserInfo(UserInfoParam userInfoParam) {
        List<UserInfoDto> userInfoDtoIPage = userRepository.selectUserInfo(userInfoParam);
        return userInfoDtoIPage;
    }
}