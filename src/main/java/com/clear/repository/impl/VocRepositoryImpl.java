package com.clear.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.clear.mapper.SailMapper;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocInfoOut;
import com.clear.repository.VocRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
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
    public VocInfoOut queryVocData(VocParam vocParam) {
        return sailMapper.queryVocData(vocParam);
    }

    @Override
    public List<SiteOut> queryUserSite(UserIdParam userIdParam) {
        List<String> stationCodeS = sailMapper.queryUserSite(userIdParam);
        //当查询关联表没有查询到数据，则直接返回空数据
        if (CollectionUtils.isEmpty(stationCodeS)) {
            return Collections.emptyList();
        }
        List<SiteOut> siteOuts = sailMapper.queryUserSiteDetail(stationCodeS);
        if(CollectionUtils.isEmpty(siteOuts)){
            return Collections.emptyList();
        }
        return siteOuts;
    }
}