package com.clear.param.input;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * ClassName UserInfoParam
 *
 * @author qml
 * Date 2020/8/13 13:25
 * Version 1.0
 **/
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class WindInfo {
    private Head header;
    private Data data;

}


