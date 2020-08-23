package com.clear.controller;

import com.clear.param.Response;
import com.clear.param.input.LoginParam;
import com.clear.service.UserInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ClassName LoginController
 *
 * @author qml
 * Date 2020/8/17 10:41
 * Version 1.0
 **/

@RestController
@RequestMapping("/authentication")
@Api(tags = "登录相关")
public class AuthenticationController {

    private static final String TOKEN = "token";

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "用户登录")
    public Response<Map<String, String>> login(@RequestBody LoginParam loginParam) throws JsonProcessingException {
        Map<String, String> resultMap = userInfoService.login(loginParam);
        if (resultMap.containsKey(TOKEN)) {
            return Response.SUCCESS(resultMap);
        } else {
            return Response.FAIL(resultMap);
        }
    }
}