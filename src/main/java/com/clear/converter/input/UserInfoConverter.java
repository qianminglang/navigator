package com.clear.converter.input;


import com.clear.entity.UserInfoDto;
import com.clear.param.input.LoginParam;
import org.mapstruct.Mapper;

/**
 * ClassName UserInfoConverter
 *
 * @author qml
 * Date 2020/8/17 13:30
 * Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface UserInfoConverter {
    /**
     * 登录入参转换
     * @author 3Clear1
     * @date 2020/8/17 13:41
      * @param loginParam
     * @return com.clear.entity.UserInfoDto
     **/
    UserInfoDto loginParam (LoginParam loginParam);
}