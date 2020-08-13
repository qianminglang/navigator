package com.clear.navigator.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.Optional;

/**
 * ClassName ParamUtil
 * 参数校验
 *
 * @author qml
 * Date 2020/8/13 17:01
 * Version 1.0
 **/

public class ParamUtil extends Exception {
    public static void isNull(Object object, String message) throws Exception {
        Optional<Object> option = Optional.ofNullable(object);
        if (option.isPresent()) {
            throw new Exception(message);
        }
    }

    public static void isEmpty(String str, String message) throws Exception {
        boolean blank = StringUtils.isBlank(str);
        if (blank) {
            throw new Exception(message);
        }
    }
}