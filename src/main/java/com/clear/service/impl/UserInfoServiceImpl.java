package com.clear.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.clear.converter.input.UserInfoConverter;
import com.clear.entity.SysUser;
import com.clear.param.input.LoginParam;
import com.clear.repository.UserInfoRepository;
import com.clear.service.UserInfoService;
import com.clear.util.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName UserInfoServiceImpl
 *
 * @author qml
 * Date 2020/8/13 10:01
 * Version 1.0
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private UserInfoConverter userInfoConverter;

//    @Resource
//    StringEncryptor stringEncryptor;

    @Override
    public Map<String, String> login(LoginParam loginParam) {
        SysUser sysUser = userInfoConverter.loginParam(loginParam);
        List<SysUser> sysUsers = userInfoRepository.selectUserInfo(sysUser);
        HashMap<String, String> resultMap = new HashMap<>(1);
        if (CollectionUtils.isEmpty(sysUsers)) {
            resultMap.put("message", "当前用户不存在");
            return resultMap;
        }

        //TODO 还未实现密码加密密码加密
//        String password = stringEncryptor.encrypt(userInfoDto.getPassWord());
        String password = sysUser.getUserpwd();
        //根据用户查询出来的用户匹配密码有没有正确的
        List<SysUser> sysUserList = sysUsers.stream().filter(e -> e.getUserpwd().equals(password)).collect(Collectors.toList());
        String userid = sysUserList.get(0).getUserid();

        if (CollectionUtils.isEmpty(sysUserList)) {
            resultMap.put("message", "当前用户密码错误");
        } else {
            String token = TokenUtil.sign(loginParam);
            resultMap.put("message", "登录成功");
            resultMap.put("token", token);
            resultMap.put("userid", userid);
        }
        return resultMap;
    }
}