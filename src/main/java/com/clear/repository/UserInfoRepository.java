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
    /**
     * 根据用户名称密码查询用户信息
     * @author 3Clear1
     * @date 2020/8/18 15:24
      * @param sysUser
     * @return java.util.List<com.clear.entity.SysUser>
     **/
    List<SysUser> selectUserInfo(SysUser sysUser);

    /**
     * 根据用户id查询用户信息
     * @author 3Clear1
     * @date 2020/8/18 15:25
      * @param userIds
     * @return java.util.List<com.clear.entity.SysUser>
     **/
    List<SysUser> selectUserInfoUserIds(List<String> userIds);
}