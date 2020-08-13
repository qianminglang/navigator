package com.clear.navigator.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clear.navigator.dto.UserInfoDto;
import com.clear.navigator.param.input.UserInfoParam;
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
public interface UserRepository {
    
    /**
     * 查询用户信息
     * @author 3Clear1
     * @date 2020/8/13 14:56
     * @param userInfoParam
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.clear.navigator.dto.UserInfoDto>
     **/
    List<UserInfoDto> selectUserInfo(@Param("item") UserInfoParam userInfoParam);

    IPage<UserInfoDto> selectUserInfoByPage(Page<UserInfoDto> userInfoDtoPage,@Param("item") UserInfoParam userInfoParam);
}