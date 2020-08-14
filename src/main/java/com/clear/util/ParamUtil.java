package com.clear.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.clear.exception.ClearArgumentException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * ClassName ParamUtil
 * 参数校验
 *
 * @author qml
 * Date 2020/8/13 17:01
 * Version 1.0
 **/

public class ParamUtil extends Exception {

    public ParamUtil() {
    }

    public static void isBlank(String string, String msg) {
        if (StringUtils.isNotBlank(string)) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void notBlank(String string, String msg) {
        if (StringUtils.isBlank(string)) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void notEmpty(Collection collection, String msg) {
        if (null == collection || collection.isEmpty()) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void nonBlankElements(Collection<String> collection, String elementMsg) {
        Iterator var2 = collection.iterator();

        while(var2.hasNext()) {
            String str = (String)var2.next();
            notBlank(str, elementMsg);
        }

    }

    public static void nonNull(Object object, String msg) {
        if (null == object) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void isNull(Object object, String msg) {
        if (null != object) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void expectTrue(boolean boolExpression, String falseMsg) {
        if (!boolExpression) {
            throw new ClearArgumentException(falseMsg);
        }
    }

    public static void expectFalse(boolean boolExpression, String trueMsg) {
        if (boolExpression) {
            throw new ClearArgumentException(trueMsg);
        }
    }

    public static void expectAnyFalse(String msg, Boolean... booleans) throws ClearArgumentException {
        if (Arrays.stream(booleans).allMatch((t) -> {
            return t;
        })) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void expectInRange(Collection collection, int minElements, int maxElements, String msg) {
        expectInRange(collection.size(), minElements, maxElements, msg);
    }

    public static void expectInRange(String string, int minLength, int maxLength, String msg) {
        if (StringUtils.isBlank(string) || string.length() < minLength || string.length() > maxLength) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void expectInRange(int value, int minValue, int maxValue, String msg) {
        if (value < minValue || value > maxValue) {
            throw new ClearArgumentException(msg);
        }
    }

    public static void expectDateStrWithPattern(String sDate, String pattern, String msg) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.parse(sDate);
        } catch (Exception var4) {
            throw new ClearArgumentException(msg);
        }
    }
}