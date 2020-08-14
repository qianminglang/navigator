package com.clear.param;

import com.clear.util.ParamUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName PageParam
 *
 * @author qml
 * Date 2020/8/14 10:14
 * Version 1.0
 **/
@Data
@AllArgsConstructor
public class PageParam extends CheckInput{

    private static final long serialVersionUID = -98424552L;

    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MIN_PAGE_SIZE = 1;
    public static final int MAX_PAGE_SIZE = 1024;

    private final int pageNo = 1;
    private final int pageSize = 20;

    @Override
    public void checkInput() {
        ParamUtil.expectTrue(this.pageNo >= 1, String.format("请求页码不能小于%d", 1));
        ParamUtil.expectInRange(this.pageSize, 1, 1024, String.format("每页条数需在[%d, %d]范围内", 1, 1024));
    }
}