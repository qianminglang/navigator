package com.clear.param.output.Param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName CurTask
 *
 * @author qml
 * Date 2020/8/18 18:47
 * Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurTask {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeS;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeE;
    private List<Integer> itemAry;
}