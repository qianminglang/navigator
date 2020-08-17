package com.clear.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clear.entity.Sail;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocInfoOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@Mapper
public interface SailMapper extends BaseMapper<Sail> {
    /**
     * 查询voc数据
     * @author 3Clear1
     * @date 2020/8/17 18:42
      * @param vocParam
     * @return com.clear.param.output.VocInfoOut
     **/
    VocInfoOut queryVocData(VocParam vocParam);

    /**
     * 查询用户关联的走航车
     * @author 3Clear1
     * @date 2020/8/17 18:54
      * @param userIdParam
     * @return java.util.List<com.clear.param.output.SiteOut>
     **/
    List<String> queryUserSite(@Param("item")UserIdParam userIdParam);

    /**
     * 查询用户关联的走航车的详细信息
     * @author 3Clear1
     * @date 2020/8/17 19:22
      * @param stationCodeS
     * @return java.util.List<com.clear.param.output.SiteOut>
     **/
    List<SiteOut> queryUserSiteDetail(@Param("items")List<String> stationCodeS);
}
