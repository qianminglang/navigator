package com.clear.repository;

import com.clear.entity.*;
import com.clear.param.input.UserIdParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryOut;
import com.clear.paramtemp.*;

import java.util.List;

/**
 * ClassName UserInfoRepository
 *
 * @author qml
 * Date 2020/8/14 15:31
 * Version 1.0
 **/

public interface VocRepository {

    /**
     * 查询走航数据
     *
     * @param userIdParam
     * @return java.util.List<com.clear.param.output.SiteOut>
     * @author 3Clear1
     * @date 2020/8/17 18:53
     **/
    List<String> queryUserSite(UserIdParam userIdParam);

    /**
     * 查询走航车的详细信息
     *
     * @param stationCodeS
     * @return java.util.List<com.clear.param.output.SiteOut>
     * @author 3Clear1
     * @date 2020/8/18 14:42
     **/
    List<SiteOut> queryUserSiteDetail(List<String> stationCodeS);

    /**
     * 查询走航车状态
     *
     * @param stationCodeS
     * @return java.util.List<com.clear.entity.Sail>
     * @author 3Clear1
     * @date 2020/8/18 14:47
     **/
    List<Sail> querySailStatus(List<String> stationCodeS);

    /**
     * 查询Instrument数据
     *
     * @param instrument
     * @return java.util.List<com.clear.entity.Instrument>
     * @author 3Clear1
     * @date 2020/8/17 20:30
     **/
    Instrument queryInstrument(Instrument instrument);

    /**
     * 查询voc数据
     *
     * @param vocTemp
     * @return com.clear.param.output.VocInfoOut
     * @author 3Clear1
     * @date 2020/8/17 18:42
     **/
    List<Data> queryVocData(VocTemp vocTemp);

    /**
     * 根据设备查询因子
     *
     * @param instrumentid
     * @return java.util.List<java.lang.String>
     * @author 3Clear1
     * @date 2020/8/18 10:33
     **/
    List<Integer> queryInstrumentParameter(Integer instrumentid);

    /**
     * 查询车的历史走航列表
     *
     * @param sailParamTemp
     * @return java.util.List<com.clear.param.output.VocHistoryOut>
     * @author 3Clear1
     * @date 2020/8/18 14:56
     **/
    List<VocHistoryOut> queryHistoryList(SailParamTemp sailParamTemp);

    /**
     * 查询voc衍生数据，表data_derived
     * @author 3Clear1
     * @date 2020/8/19 17:14
      * @param vocDerviedTemp
     * @return java.util.List<com.clear.entity.DataDerived>
     **/
    List<DataDerived> queryDerivedsVocData(VocDerviedTemp vocDerviedTemp);

    /**
     * 查询data_voc表,得到voc以微克/立方
     * @author 3Clear1
     * @date 2020/8/20 13:46
      * @param vocTemp
     * @return java.util.List<com.clear.entity.DataVoc>
     **/
    List<DataVoc> getUgm3Data(VocTemp vocTemp);

    /**
     * 查询单因子在时间范围和经纬度范围内的数据
     * @author 3Clear1
     * @date 2020/8/22 10:37
      * @param oneVocParamTemp
     * @return java.util.List<com.clear.entity.Data>
     **/
    List<Data> queryOneVodData(OneVocParamTemp oneVocParamTemp);

    /**
     * 根据认为ids查询sail_parameter表
     * @author 3Clear1
     * @date 2020/8/22 16:47
      * @param sailIds
     * @return java.util.List<com.clear.entity.SailParameter>
     **/
    List<SailParameter> queryParametersBySailIds(List<Integer> sailIds);
}