package com.clear.repository.impl;

import com.clear.entity.SysUser;
import com.clear.mapper.UserMapper;
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
    public List<SysUser> selectUserInfo(SysUser sysUser) {
        return userMapper.selectUserInfo(sysUser);
    }

    @Override
    public List<SysUser> selectUserInfoUserIds(List<String> userIds) {
        return userMapper.selectUserInfoUserIds(userIds);
    }
}