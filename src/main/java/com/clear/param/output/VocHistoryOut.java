package com.clear.param.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
@ApiModel
public class VocHistoryOut {

    @ApiModelProperty(value = "任务id")
    private Long taskId;

    @ApiModelProperty(value = "任务开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeS;

    @ApiModelProperty(value = "开始用户id")
    private String startUserId;

    @ApiModelProperty(value = "开始用户名")
    private String startUserName;

    @ApiModelProperty(value = "任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String timeE;


    @ApiModelProperty(value = "结束用户id")
    private String endUserId;

    @ApiModelProperty(value = "结束用户名")
    private String endUserName;

    @ApiModelProperty(value = "车辆id")
    private String busID;

    @ApiModelProperty(value = "因子id")
    private List<Integer> itemAry;
}