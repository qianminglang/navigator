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
public class ItemInfoOut {

    @ApiModelProperty(value = "因子id")
    private Integer id;

    @ApiModelProperty(value = "因子名称")
    private String name;

    @ApiModelProperty(value = "是否展示")
    private Integer show;

    @ApiModelProperty(value = "最小值")
    private Integer min;

    @ApiModelProperty(value = "最大值")
    private Integer max;

    @ApiModelProperty(value = "最大ug")
    private Integer maxug;

    @ApiModelProperty(value = "td")
    private Integer td;

    @ApiModelProperty(value = "tdug")
    private Integer tdug;

    @ApiModelProperty(value = "scale")
    private Integer scale;

    @ApiModelProperty(value = "scaleug")
    private Integer scaleug;

    @ApiModelProperty(value = "index")
    private Integer index;

    @ApiModelProperty(value = "values")
    private List<Integer> values;

    @ApiModelProperty(value = "valuesug")
    private List<Integer> valuesug;

    @ApiModelProperty(value = "industry")
    private List<String> industry;



}