package com.clear.converter.input;


import com.clear.entity.Sail;
import com.clear.param.output.LatestBusInfoOut;
import com.clear.paramtemp.VocTemp;
import com.clear.param.input.VocParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * ClassName UserInfoConverter
 *
 * @author qml
 * Date 2020/8/17 13:30
 * Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface SailInfoConverter {
    /**
     * 查询voc入参转换
     *
     * @param vocParam
     * @return com.clear.entity.UserInfoDto
     * @author 3Clear1
     * @date 2020/8/17 13:41
     **/
    @Mappings({
            @Mapping(source = "stationCode", target = "stationcode")
    })
    VocTemp vocParamConvert(VocParam vocParam);

    /**
     * 车辆最新信息状态转换
     *
     * @param sail
     * @return com.clear.param.output.LatestBusInfoOut
     * @author 3Clear1
     * @date 2020/8/20 15:32
     **/
    @Mappings({
            @Mapping(source = "stationId", target = "busID"),
            @Mapping(source = "sailId", target = "taskID"),
            @Mapping(source = "startTime", target = "timeS"),
            @Mapping(source = "endTime", target = "timeE")
    })
    LatestBusInfoOut busInfoVonvert(Sail sail);
}