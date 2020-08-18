package com.clear.repository;

import com.clear.paramtemp.SailParamTemp;
import com.clear.paramtemp.VocTemp;
import com.clear.entity.Data;
import com.clear.entity.Instrument;
import com.clear.entity.Sail;
import com.clear.param.input.UserIdParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryOut;

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
     * @author 3Clear1
     * @date 2020/8/18 14:42
      * @param stationCodeS
     * @return java.util.List<com.clear.param.output.SiteOut>
     **/
    List<SiteOut> queryUserSiteDetail(List<String> stationCodeS);

    /**
     * 查询走航车状态
     * @author 3Clear1
     * @date 2020/8/18 14:47
      * @param stationCodeS
     * @return java.util.List<com.clear.entity.Sail>
     **/
    List<Sail> querySailStatus(List<String> stationCodeS);

    /**
     * 查询Instrument数据
     * @author 3Clear1
     * @date 2020/8/17 20:30
      * @param instrument
     * @return java.util.List<com.clear.entity.Instrument>
     **/
    List<Instrument> queryInstrument(Instrument instrument);

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
     * @author 3Clear1
     * @date 2020/8/18 10:33
      * @param instrumentid
     * @return java.util.List<java.lang.String>
     **/
    List<Integer> queryInstrumentParameter(Integer instrumentid);

    /**
     * 查询车的历史走航列表
     * @author 3Clear1
     * @date 2020/8/18 14:56
      * @param sailParamTemp
     * @return java.util.List<com.clear.param.output.VocHistoryOut>
     **/
    List<VocHistoryOut> queryHistoryList(SailParamTemp sailParamTemp);
}