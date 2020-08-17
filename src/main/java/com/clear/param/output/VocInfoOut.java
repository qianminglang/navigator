package com.clear.param.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName UserInfoOut
 *
 * @author qml
 * Date 2020/8/13 13:33
 * Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocInfoOut {
    private List<Long> timeAry;
    private List<List<Float>> ptAry;
    private List<List<Float>> dataAry;
}