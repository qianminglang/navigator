package com.clear.repository.impl;

import com.clear.entity.*;
import com.clear.mapper.SailMapper;
import com.clear.mapper.SailParameterMapper;
import com.clear.param.input.UserIdParam;
import com.clear.param.output.ParameterInfoOut;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryOut;
import com.clear.paramtemp.OneVocParamTemp;
import com.clear.paramtemp.SailParamTemp;
import com.clear.paramtemp.VocDerviedTemp;
import com.clear.paramtemp.VocTemp;
import com.clear.repository.VocRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName UserInfoRepositoryImpl
 *
 * @author qml
 * Date 2020/8/14 15:32
 * Version 1.0
 **/
@Repository
public class VocRepositoryImpl implements VocRepository {

    @Resource
    private SailMapper sailMapper;

    @Resource
    private SailParameterMapper sailParameterMapper;

    @Override
    public List<String> queryUserSite(UserIdParam userIdParam) {
        return sailMapper.queryUserSite(userIdParam);
    }

    @Override
    public List<SiteOut> queryUserSiteDetail(List<String> stationCodeS) {
        return sailMapper.queryUserSiteDetail(stationCodeS);
    }

    @Override
    public List<Sail> querySailStatus(List<String> stationCodeS) {
        return sailMapper.querySailStatus(stationCodeS);
    }


    @Override
    public List<Instrument> queryInstrument(Instrument instrument) {
        return sailMapper.queryInstrument(instrument);
    }

    @Override
    public List<Data> queryVocData(VocTemp vocTemp) {
        return sailMapper.queryVocData(vocTemp);
    }

    @Override
    public List<Integer> queryInstrumentParameter(Integer instrumentid) {
        return sailMapper.queryInstrumentParameter(instrumentid);
    }

    @Override
    public List<VocHistoryOut> queryHistoryList(SailParamTemp sailParamTemp) {
        return sailMapper.queryHistoryList(sailParamTemp);
    }

    @Override
    public List<DataDerived> queryDerivedsVocData(VocDerviedTemp vocDerviedTemp) {
        return sailMapper.queryDerivedsVocData(vocDerviedTemp);
    }

    @Override
    public List<DataVoc> getUgm3Data(VocTemp vocTemp) {
        return sailMapper.getUgm3Data(vocTemp);
    }

    @Override
    public List<Data> queryOneVodData(OneVocParamTemp oneVocParamTemp) {
        return sailMapper.queryOneVodData(oneVocParamTemp);
    }

    @Override
    public List<SailParameter> queryParametersBySailIds(List<Long> sailIds) {
        return sailMapper.queryParametersBySailIds(sailIds);
    }

    @Override
    public List<Sail> querySailAll() {
        return sailMapper.querySailAll();
    }

    @Override
    public List<Integer> queryParametersByParam(Sail sail) {
        return sailMapper.queryParametersByParam(sail);
    }

    @Override
    public int insertSailParameter(SailParameter sailParameter) {
        return sailParameterMapper.insert(sailParameter);
    }

    @Override
    public List<Integer> selectInstrumentparameters(int i) {
        return sailMapper.selectInstrumentparameters(i);
    }

    @Override
    public List<ParameterInfoOut> selectParameters(List<Integer> parameters) {
        return sailMapper.selectParameters(parameters);
    }
}