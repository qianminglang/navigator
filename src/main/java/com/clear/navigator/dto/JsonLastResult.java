package com.clear.navigator.dto;

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
    private List<Long> time;
    private List<List<Float>> lonLat;
    private List<List<Float>> value;
}