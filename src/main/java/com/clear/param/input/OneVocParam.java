package com.clear.param.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class OneVocParam {
    @ApiModelProperty(value = "因子")
    private Integer parameterid;

    @ApiModelProperty(value = "车id")
    private String stationcode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "开始经度")
    private Float startLon;

    @ApiModelProperty(value = "结束经度")
    private Float endLon;

    @ApiModelProperty(value = "开始纬度")
    private Float startLat;

    @ApiModelProperty(value = "结束纬度")
    private Float endLat;
}