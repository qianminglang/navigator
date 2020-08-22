package com.clear.param.input;

import com.clear.param.output.RequestParam;
import com.clear.util.ParamUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class VocParam extends RequestParam {
    private String stationCode;
    private Integer sailId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @Override
    public void checkInput() {
        super.checkInput();
        ParamUtil.notBlank(stationCode, "导航车编号不能为空");
    }
}