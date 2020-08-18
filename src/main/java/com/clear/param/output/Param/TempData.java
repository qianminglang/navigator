package com.clear.param.output.Param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName TempData
 *
 * @author qml
 * Date 2020/8/18 19:54
 * Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempData {
    private String cn;
    private String en;
}