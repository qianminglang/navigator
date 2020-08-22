package com.clear.param.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName UserInfoParam
 *
 * @author qml
 * Date 2020/8/13 13:25
 * Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneVocParam {
    //因子
    private Integer parameterid;
    //设备
    private Integer instrumentid;
    //车
    private String stationcode;
    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    //开始经度
    private Float startLon;
    //结束经度
    private Float endLon;
    //开始纬度
    private Float startLat;
    //结束纬度
    private Float endLat;
}