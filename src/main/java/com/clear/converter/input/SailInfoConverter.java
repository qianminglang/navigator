package com.clear.converter.input;


import com.clear.entity.Sail;
import com.clear.param.input.OneVocParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.LatestBusInfoOut;
import com.clear.paramtemp.LonLatRangeTemp;
import com.clear.paramtemp.OneVocParamTemp;
import com.clear.paramtemp.VocTemp;
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

    /**
     * 查询单因子在时间范围和经纬度范围内的数据入参转换
     *
     * @param oneVocParam
     * @return com.clear.paramtemp.OneVocParamTemp
     * @author 3Clear1
     * @date 2020/8/22 10:51
     **/
    @Mappings({
            @Mapping(source = "startTime", target = "startTime"),
            @Mapping(source = "endTime", target = "endTime"),
    })
    OneVocParamTemp oneVocParamConvert(OneVocParam oneVocParam);

    /**
     * 查询ugm3入参转换
     *
     * @param oneVocParam
     * @return com.clear.paramtemp.VocTemp
     * @author 3Clear1
     * @date 2020/8/22 13:36
     **/
    @Mappings({
            @Mapping(target = "stationcode", source = "stationcode"),
            @Mapping(target = "startTime", source = "startTime"),
            @Mapping(target = "endTime", source = "endTime")
    })
    VocTemp oneUgm3Convert(OneVocParam oneVocParam);

    /**
     * 经纬度范围转换
     *
     * @param oneVocParam
     * @return com.clear.paramtemp.LonLatRangeTemp
     * @author 3Clear1
     * @date 2020/8/22 14:05
     **/
    @Mappings({
            @Mapping(target = "startLon", source = "startLon"),
            @Mapping(target = "endLon", source = "endLon"),
            @Mapping(target = "startLat", source = "startLat"),
            @Mapping(target = "endLat", source = "endLat"),
    })
    LonLatRangeTemp lonLatRangeConvert(OneVocParam oneVocParam);
}