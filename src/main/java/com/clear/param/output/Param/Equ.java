package com.clear.param.output.Param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName Equ
 *
 * @author qml
 * Date 2020/8/18 18:47
 * Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class Equ {

    @ApiModelProperty(value = "")
    private String equID;

    @ApiModelProperty(value = "因子id")
    private List<Item> itemAry;
}