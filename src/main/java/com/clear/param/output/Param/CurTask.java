package com.clear.param.output.Param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class CurTask {

    @ApiModelProperty(value = "任务id")
    private Long id;

    @ApiModelProperty(value = "  @ApiModelProperty(value = \"\")")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeS;

    @ApiModelProperty(value = "任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timeE;

    @ApiModelProperty(value = "车辆下设备id")
    private List<Integer> itemAry;
}