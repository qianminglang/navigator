package com.clear.param.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class LoginParam {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String passWord;
}