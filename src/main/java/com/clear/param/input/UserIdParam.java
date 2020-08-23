package com.clear.param.input;

import com.clear.param.output.RequestParam;
import com.clear.util.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@ApiModel
public class UserIdParam extends RequestParam {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @Override
    public void checkInput() {
        super.checkInput();
        ParamUtil.notBlank(userId,"用户id不能为空");
    }
}