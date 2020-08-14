package com.clear.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.clear.entity.UserInfoDto;
import com.clear.param.Response;
import com.clear.param.input.UserInfoParam;
import com.clear.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName HelloWorld
 *
 * @author qml
 * Date 2020/8/13 9:14
 * Version 1.0
 **/

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/select")
    public Response<List<UserInfoDto>> userSelect(@RequestBody UserInfoParam userInfoParam) {
        return new Response<>(userInfoService.selectUserInfo(userInfoParam));
    }

    @RequestMapping("/selectByPage")
    public Response<IPage<UserInfoDto>> selectByPage(@RequestBody UserInfoParam userInfoParam) {
        userInfoParam.checkInput();
        return new Response<>(userInfoService.selectUserInfoByPage(userInfoParam));
    }
}