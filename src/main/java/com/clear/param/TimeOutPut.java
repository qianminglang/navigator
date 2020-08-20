package com.clear.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName TimeOutPut
 *
 * @author qml
 * Date 2020/8/20 18:10
 * Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeOutPut {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private String duration;
}