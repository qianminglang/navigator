package com.clear.paramtemp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName UserInfo
 *
 * @author qml
 * Date 2020/8/13 9:59
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LonLatRangeTemp {

    //开始经度
    private Float startLon;
    //结束经度
    private Float endLon;
    //开始纬度
    private Float startLat;
    //结束纬度
    private Float endLat;
}