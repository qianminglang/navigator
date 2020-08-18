package com.clear.param.output;

import com.clear.param.output.Param.CurTask;
import com.clear.param.output.Param.Equ;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName UserInfoOut
 *
 * @author qml
 * Date 2020/8/13 13:33
 * Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteOut {
    private String id;
    private String name;
    private String orderid;
    private String vedioInfo;
    private String modelUrl;
    private String modelOffset;
    private String freSecd;

    //是否在走航中，1表示在走航中，0表示停止
    private Integer sailing;


    /**
     * 走航开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 开始操作用户id
     */
    private String startUserId;

    /**
     * 走航结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /**
     * 结束操作用户id
     */
    private String endUserId;

    private CurTask curTask;

    private List<Equ> equAry;

}