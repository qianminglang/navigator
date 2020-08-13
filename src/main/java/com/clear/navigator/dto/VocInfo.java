package com.clear.navigator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName VocInfo
 *
 * @author qml
 * Date 2020/8/6 17:17
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VocInfo {
    private String time;
    private String name;
    private String value;
}