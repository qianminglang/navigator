package com.clear.paramtemp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
public class OneVocParamTemp {


    private List<Integer> parameterids;
    //设备
    private  List<Integer> instrumentids;
    //车
    private String stationcode;

    private Date startTime;

    private Date endTime;
}