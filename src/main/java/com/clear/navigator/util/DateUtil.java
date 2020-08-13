package com.clear.navigator.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName DateUtil
 *
 * @author qml
 * Date 2020/8/11 11:14
 * Version 1.0
 **/
@Slf4j
public class DateUtil {
    public static final String YYYY_M_DD_HH_MM_SS = "yyyy/M/ddHH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 字符串转Date类型
     */
    public static Date stringToDate(String str, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            log.error("String 转换 Date 失败， {}", e.getMessage());
        }
        return date;
    }

    /**
     *  Date转字符串类型
     */
    public static String dateToString(Date date, String type) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        return simpleDateFormat.format(date);
    }
}