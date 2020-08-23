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
public class VocHisTimeInfoOut {

    @ApiModelProperty(value = "时间列表")
    private List<Long> timeAry;

    @ApiModelProperty(value = "经度，纬度，海拔列表")
    private List<List<Float>> ptAry;

    @ApiModelProperty(value = "voc的pvb数据列表")
    private List<List<Float>> dataAryPVB;

    @ApiModelProperty(value = "voc的ugm3列表")
    private List<List<Float>> dataAryUgm3;

    @ApiModelProperty(value = "voc的诊断量列表")
    private List<List<Float>> dataAryZhenDuan;
}