package com.clear.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clear.entity.*;
import com.clear.param.input.UserIdParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryOut;
import com.clear.paramtemp.OneVocParamTemp;
import com.clear.paramtemp.SailParamTemp;
import com.clear.paramtemp.VocDerviedTemp;
import com.clear.paramtemp.VocTemp;
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
     *
     * @param stationCodeS
     * @return java.util.List<com.clear.entity.Sail>
     * @author 3Clear1
     * @date 2020/8/18 9:43
     **/
    List<Sail> querySailStatus(@Param("items") List<String> stationCodeS);

    /**
     * 根据设置查询因子
     *
     * @param instrumentid
     * @return java.util.List<java.lang.String>
     * @author 3Clear1
     * @date 2020/8/18 10:35
     **/
    List<Integer> queryInstrumentParameter(Integer instrumentid);

    /**
     * 查询车的历史走航列表
     *
     * @param sailParamTemp
     * @return java.util.List<com.clear.param.output.VocHistoryOut>
     * @author 3Clear1
     * @date 2020/8/18 14:57
     **/
    List<VocHistoryOut> queryHistoryList(@Param("item") SailParamTemp sailParamTemp);

    /**
     * 查询voc衍生数据，表data_derived
     *
     * @param vocDerviedTemp
     * @return java.util.List<com.clear.entity.DataDerived>
     * @author 3Clear1
     * @date 2020/8/19 17:14
     **/
    List<DataDerived> queryDerivedsVocData(@Param("item") VocDerviedTemp vocDerviedTemp);

    /**
     * 查询voc数据
     *
     * @param vocTemp
     * @return com.clear.param.output.VocInfoOut
     * @author 3Clear1
     * @date 2020/8/17 18:42
     **/
    List<DataVoc> getUgm3Data(@Param("item") VocTemp vocTemp);

    /**
     * 查询单因子在时间范围和经纬度范围内的数据
     * @author 3Clear1
     * @date 2020/8/22 10:37
      * @param oneVocParamTemp
     * @return java.util.List<com.clear.entity.Data>
     **/
    List<Data> queryOneVodData(@Param("item")OneVocParamTemp oneVocParamTemp);

    /**
     * 根据认为ids查询sail_parameter表
     * @author 3Clear1
     * @date 2020/8/22 16:43
      * @param sailIds
     * @return java.util.List<com.clear.entity.SailParameter>
     **/
    List<SailParameter> queryParametersBySailIds(@Param("sailIds") List<Long> sailIds);

    /**
     * 查询所有的历史走航任务
     * @author 3Clear1
     * @date 2020/8/23 12:04
      * @param 
     * @return java.util.List<com.clear.entity.Sail>
     **/
    List<Sail> querySailAll();

    /**
     * 查询data数据表中有哪些因子
     * @author 3Clear1
     * @date 2020/8/23 12:12
      * @param sail
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> queryParametersByParam(@Param("item") Sail sail);
}
