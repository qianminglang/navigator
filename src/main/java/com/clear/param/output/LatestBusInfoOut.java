package com.clear.param.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class LatestBusInfoOut {

    @ApiModelProperty(value = "车id")
    private Long busID;

    @ApiModelProperty(value = "是否是运行状态：1运行，0结束")
    private Integer sailing;

    @ApiModelProperty(value = "任务id")
    private Long taskID;

    @ApiModelProperty(value = "任务开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeS;

    @ApiModelProperty(value = "任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeE;

    @ApiModelProperty(value = "因子id")
    private List<Integer> itemAry;
}