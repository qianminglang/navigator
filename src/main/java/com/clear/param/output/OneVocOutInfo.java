package com.clear.param.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class OneVocOutInfo {
    private List<Long> timeAry;
    private List<List<Float>> ptAry;
    private List<Float> dataAryPVB;
    private List<Float> dataAryUgm3;
    private List<List<Float>> dataAryZhenDuan;
}