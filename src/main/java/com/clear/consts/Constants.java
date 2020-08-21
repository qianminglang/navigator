package com.clear.consts;

import java.time.format.DateTimeFormatter;

/**
 * ClassName Constants
 *
 * @author qml
 * Date 2020/8/17 20:15
 * Version 1.0
 **/

public class Constants {
    /**
     * VOC
     *
     * @author 3Clear1
     * @date 2020/8/17 20:41
     * @param null
     * @return
     **/
    public static final String VOC = "VOC";
    public static final String GPS = "GPS";

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    /**
     * @author 3Clear1
     * @date 2020/8/17 20:41
     * @param null
     * @return
     **/
    public static final Long ONE_SECOND = 1L;

    public static final Integer INTEGER_2 = 2;

    public static final Integer INTEGER_5 = 5;

    public static final Integer INTEGER_31 = 31;

    public static final Integer INTEGER_32 = 32;
    public static final Integer INTEGER_211 = 211;

    public static final Integer INTEGER_70 = 70;

    public static final Integer INTEGER_150 = 150;
    public static final Integer INTEGER_0 = 0;
    public static final Integer INTEGER_60 = 60;
}