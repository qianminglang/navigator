package com.clear.converter.input;


import com.clear.domain.VocTemp;
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
     * @author 3Clear1
     * @date 2020/8/17 13:41
      * @param vocParam
     * @return com.clear.entity.UserInfoDto
     **/
    @Mappings({
            @Mapping(source = "stationCode", target = "stationcode")
    })
    VocTemp vocParamConvert (VocParam vocParam);
}