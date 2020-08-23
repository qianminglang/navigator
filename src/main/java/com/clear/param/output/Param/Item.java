package com.clear.param.output.Param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName Item
 *
 * @author qml
 * Date 2020/8/18 18:48
 * Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class Item {

    @ApiModelProperty(value = "因子id")
    private String id;

    @ApiModelProperty(value = "是否展示")
    private Integer show;
}