package com.clear.navigator.controller;

import com.clear.navigator.dto.UserInfoDto;
import com.clear.navigator.param.input.UserInfoParam;
import com.clear.navigator.service.UserInfoService;
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
    public List<UserInfoDto> userSelect(@RequestBody UserInfoParam userInfoParam) {
        return userInfoService.selectUserInfo(userInfoParam);
    }
}