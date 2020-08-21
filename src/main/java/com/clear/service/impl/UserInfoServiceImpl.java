package com.clear.service.impl;

import cn.hutool.crypto.digest.BCrypt;
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

        String userid = "";
        boolean flag = false;
        for (SysUser user : sysUsers) {
            //查询到的密码
            String userpwd = user.getUserpwd();
            //入参密码
            String passWord = loginParam.getPassWord();
            flag = BCrypt.checkpw(passWord, userpwd);
            userid = user.getUserid();
            if (flag) {
                break;
            }
        }
        //根据用户查询出来的用户匹配密码有没有正确的
        if (flag) {
            String token = TokenUtil.sign(loginParam);
            resultMap.put("message", "登录成功");
            resultMap.put("token", token);
            resultMap.put("userid", userid);
        } else {
            resultMap.put("message", "当前用户密码错误");
        }
        return resultMap;
    }
}