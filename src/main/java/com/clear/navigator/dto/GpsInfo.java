package com.clear.navigator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName GpsInfo
 *
 * @author qml
 * Date 2020/8/6 16:40
 * Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GpsInfo {
    private String time;

    private String lon;

    private String lat;
}