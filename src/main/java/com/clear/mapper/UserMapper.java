package com.clear.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clear.entity.SysUser;
import com.clear.entity.UserInfoDto;
import com.clear.param.input.UserInfoParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName UserRepository
 *
 * @author qml
 * Date 2020/8/13 9:38
 * Version 1.0
 **/
@Mapper
public interface UserMapper {
    
    /**
     * 查询用户信息
     * @author 3Clear1
     * @date 2020/8/13 14:56
     * @param sysUser
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.clear.navigator.dto.UserInfoDto>
     **/
    List<SysUser> selectUserInfo(@Param("item") SysUser sysUser);

    /**
     * 分页查询用户信息
     * @author 3Clear1
     * @date 2020/8/13 16:22
     * @param userInfoDtoPage
     * @param userInfoParam
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.clear.navigator.dto.UserInfoDto>
     **/
    IPage<UserInfoDto> selectUserInfoByPage(Page<UserInfoDto> userInfoDtoPage,@Param("item") UserInfoParam userInfoParam);
}