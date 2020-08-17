package com.clear.converter.input;


import com.clear.entity.SysUser;
import com.clear.param.input.LoginParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
    @Mappings({
            @Mapping(source = "userName", target = "username"),
            @Mapping(source = "passWord", target = "userpwd")
    })
    SysUser loginParam (LoginParam loginParam);
}