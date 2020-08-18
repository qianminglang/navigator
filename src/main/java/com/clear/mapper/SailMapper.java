package com.clear.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clear.paramtemp.SailParamTemp;
import com.clear.paramtemp.VocTemp;
import com.clear.entity.Data;
import com.clear.entity.Instrument;
import com.clear.entity.Sail;
import com.clear.param.input.UserIdParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@Mapper
public interface SailMapper extends BaseMapper<Sail> {

    /**
     * 查询用户关联的走航车
     *
     * @param userIdParam
     * @return java.util.List<com.clear.param.output.SiteOut>
     * @author 3Clear1
     * @date 2020/8/17 18:54
     **/
    List<String> queryUserSite(@Param("item") UserIdParam userIdParam);

    /**
     * 查询用户关联的走航车的详细信息
     *
     * @param stationCodeS
     * @return java.util.List<com.clear.param.output.SiteOut>
     * @author 3Clear1
     * @date 2020/8/17 19:22
     **/
    List<SiteOut> queryUserSiteDetail(@Param("items") List<String> stationCodeS);


    /**
     * 查询Instrument数据
     *
     * @param instrument
     * @return java.util.List<com.clear.entity.Instrument>
     * @author 3Clear1
     * @date 2020/8/17 20:16
     **/
    List<Instrument> queryInstrument(@Param("item") Instrument instrument);

    /**
     * 查询voc数据
     *
     * @param vocTemp
     * @return com.clear.param.output.VocInfoOut
     * @author 3Clear1
     * @date 2020/8/17 18:42
     **/
    List<Data> queryVocData(@Param("item") VocTemp vocTemp);

    /**
     * 查询走航车的状态
     * @author 3Clear1
     * @date 2020/8/18 9:43
      * @param stationCodeS
     * @return java.util.List<com.clear.entity.Sail>
     **/
    List<Sail> querySailStatus(@Param("items")List<String> stationCodeS);

    /**
     * 根据设置查询因子
     * @author 3Clear1
     * @date 2020/8/18 10:35
      * @param instrumentid
     * @return java.util.List<java.lang.String>
     **/
    List<Integer> queryInstrumentParameter(Integer instrumentid);
    
    /**
     * 查询车的历史走航列表
     * @author 3Clear1
     * @date 2020/8/18 14:57
      * @param sailParamTemp
     * @return java.util.List<com.clear.param.output.VocHistoryOut>
     **/
    List<VocHistoryOut> queryHistoryList(@Param("item")SailParamTemp sailParamTemp);
}
