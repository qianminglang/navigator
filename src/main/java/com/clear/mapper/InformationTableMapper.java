package com.clear.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clear.entity.InformationTable;
import com.clear.param.input.UserInfoParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-14
 */
@Mapper
public interface InformationTableMapper extends BaseMapper<InformationTable> {
    /**
     * 分页查询用户信息
     * @author 3Clear1
     * @date 2020/8/13 16:22
     * @param userInfoDtoPage
     * @param userInfoParam
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.clear.navigator.dto.UserInfoDto>
     **/
    IPage<InformationTable> selectUserInfoByPage(Page<InformationTable> userInfoDtoPage, @Param("item") UserInfoParam userInfoParam);
}
