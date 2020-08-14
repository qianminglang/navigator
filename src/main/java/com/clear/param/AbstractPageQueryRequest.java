package com.clear.param;

import com.clear.util.ParamUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName AbstractPageQueryRequest
 *
 * @author qml
 * Date 2020/8/13 16:49
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractPageQueryRequest extends PageParam {

    private PageParam pageParam;

    /**
     * 校验参数
     *
     * @param
     * @return void
     * @author 3Clear1
     * @date 2020/8/13 16:58
     **/
    @Override
    public void checkInput() {
        ParamUtil.nonNull(this.pageParam, "分页方式不能为空");
        this.pageParam.checkInput();
    }
}