package com.clear.navigator.param;

import com.clear.navigator.util.ParamUtil;
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
public abstract class AbstractPageQueryRequest {

    private PageInfo pageInfo;

    /**
     * 校验参数
     *
     * @param
     * @return void
     * @author 3Clear1
     * @date 2020/8/13 16:58
     **/
    public void checkInPut() throws Exception {
        ParamUtil.isNull(pageInfo.page, "分页查询页码不能为空");
        ParamUtil.isNull(pageInfo.size, "分页查询长度不能为空");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class PageInfo {
        private int page;
        private int size;
    }
}