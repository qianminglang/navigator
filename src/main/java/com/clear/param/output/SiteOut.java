package com.clear.param.output;

import com.clear.param.output.Param.CurTask;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class SiteOut {

    @ApiModelProperty(value = "车id")
    private String id;

    @ApiModelProperty(value = "车名称")
    private String name;

    @ApiModelProperty(value = "")
    private String orderid;

    @ApiModelProperty(value = "")
    private String vedioInfo;

    @ApiModelProperty(value = "")
    private String modelUrl;

    @ApiModelProperty(value = "")
    private String modelOffset;

    @ApiModelProperty(value = "")
    private String freSecd;

    @ApiModelProperty(value = "是否在走航中，1表示在走航中，0表示停止")
    private Integer sailing;

    @ApiModelProperty(value = "走航开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "开始操作用户id")
    private String startUserId;

    @ApiModelProperty(value = "走航结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "结束操作用户id")
    private String endUserId;

    @ApiModelProperty(value = "当前任务")
    private CurTask curTask;

    @ApiModelProperty(value = "设备id列表")
    private List<Integer> equAry;

}