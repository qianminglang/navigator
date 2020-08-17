package com.clear.repository;

import com.clear.entity.SysUser;

import java.util.List;

/**
 * ClassName UserInfoRepository
 *
 * @author qml
 * Date 2020/8/14 15:31
 * Version 1.0
 **/

public interface UserInfoRepository {
    List<SysUser> selectUserInfo(SysUser sysUser);


}