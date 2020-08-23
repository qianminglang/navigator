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
public class VocRealInfoOut {

    @ApiModelProperty(value = "时间")
    private Long time;

    @ApiModelProperty(value = "经度，纬度，海拔")
    private List<Float> pt;

    @ApiModelProperty(value = "voc数据列表")
    private List<Float> dataAry;
}