package com.clear.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.clear.domain.VocTemp;
import com.clear.entity.Data;
import com.clear.entity.Instrument;
import com.clear.entity.Sail;
import com.clear.mapper.SailMapper;
import com.clear.param.input.UserIdParam;
import com.clear.param.output.SiteOut;
import com.clear.repository.VocRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<SiteOut> queryUserSite(UserIdParam userIdParam) {
        List<String> stationCodeS = sailMapper.queryUserSite(userIdParam);
        //当查询关联表没有查询到数据，则直接返回空数据
        if (CollectionUtils.isEmpty(stationCodeS)) {
            return Collections.emptyList();
        }
        //查询走航车名称
        List<SiteOut> siteOuts = sailMapper.queryUserSiteDetail(stationCodeS);
        if(CollectionUtils.isEmpty(siteOuts)){
            return Collections.emptyList();
        }
        //查询走航车的状态，取每个走航车最新的状态
        List<Sail> siteStatus=sailMapper.querySailStatus(stationCodeS);
        if(CollectionUtils.isNotEmpty(siteStatus)){
            Map<String, Sail> sailMap = siteStatus.stream().collect(Collectors.toMap(Sail::getStationId, item -> item, (oldValue, newValue) -> newValue));
            for (SiteOut siteOut : siteOuts) {
                Sail sail = sailMap.get(siteOut.getStationcode());
                siteOut.setStartTime(sail.getStartTime());
                siteOut.setEndTime(sail.getEndTime());
                siteOut.setEndUserId(sail.getEndUserId());
                siteOut.setStartUserId(sail.getStartUserId());
            }
        }
        return siteOuts;
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
}