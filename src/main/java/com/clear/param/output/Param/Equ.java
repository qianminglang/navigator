package com.clear.param.output.Param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName Equ
 *
 * @author qml
 * Date 2020/8/18 18:47
 * Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equ {
    private String equID;
    private List<Item> itemAry;
}