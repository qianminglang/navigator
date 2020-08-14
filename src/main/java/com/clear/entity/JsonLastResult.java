package com.clear.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName JsonResult
 *
 * @author qml
 * Date 2020/8/6 18:35
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonLastResult implements Serializable {
    private List<Long> timeAry;
    private List<List<Float>> ptAry;
    private List<List<Float>> dataAry;
}