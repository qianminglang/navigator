package com.clear.navigator.param.input;

import com.clear.navigator.param.AbstractPageQueryRequest;
import com.clear.navigator.util.ParamUtil;
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
public class UserInfoParam extends AbstractPageQueryRequest {
    private String username;
    private String password;

    @Override
    public void checkInput() {
        super.checkInput();
        ParamUtil.notBlank(username,"用户名称不能为空");
    }
}