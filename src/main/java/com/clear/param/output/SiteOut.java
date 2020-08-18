package com.clear.param.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String stationcode;
    private String name;
    private String orderid;

    /**
     * 走航开始时间
     */
    private LocalDateTime startTime;

    /**
     * 开始操作用户id
     */
    private String startUserId;

    /**
     * 走航结束时间
     */
    private LocalDateTime endTime;

    /**
     * 结束操作用户id
     */
    private String endUserId;
}