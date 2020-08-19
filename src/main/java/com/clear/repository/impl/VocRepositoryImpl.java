package com.clear.repository.impl;

import com.clear.paramtemp.SailParamTemp;
import com.clear.paramtemp.VocTemp;
import com.clear.entity.Data;
import com.clear.entity.Instrument;
import com.clear.entity.Sail;
import com.clear.mapper.SailMapper;
import com.clear.param.input.UserIdParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryOut;
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
    public Instrument queryInstrument(Instrument instrument) {
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
}