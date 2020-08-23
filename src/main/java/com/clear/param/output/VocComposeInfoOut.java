package com.clear.param.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class VocComposeInfoOut {

    @ApiModelProperty(value = "时间列表")
    private List<Long> timeAry;

    @ApiModelProperty(value = "经纬度列表")
    private List<List<Float>> ptAry;

    @ApiModelProperty(value = "诊断量数据")
    private List<List<Float>> comAry;
}