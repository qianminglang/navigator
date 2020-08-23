package com.clear.param.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class LatestBusInfoOut {
    private Long busID;
    private Integer sailing;
    private Long taskID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeS;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeE;
    private List<Integer> itemAry;
}