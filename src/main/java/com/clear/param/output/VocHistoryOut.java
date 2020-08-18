package com.clear.param.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class VocHistoryOut {
    private Long sailId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    private String startUserId;
    private String startUserName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;
    private String endUserId;
    private String endUserName;
    private String stationId;

}