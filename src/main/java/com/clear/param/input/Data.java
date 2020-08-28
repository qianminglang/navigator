package com.clear.param.input;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName Data
 *
 * @author qml
 * Date 2020/8/27 19:25
 * Version 1.0
 **/

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private List<Float> one;
    private List<Float> two;
    private String test;
}