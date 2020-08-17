package com.clear.repository;

import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocInfoOut;

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
     * 查询voc数据
     *
     * @param vocParam
     * @return com.clear.param.output.VocInfoOut
     * @author 3Clear1
     * @date 2020/8/17 18:42
     **/
    VocInfoOut queryVocData(VocParam vocParam);

    /**
     * 查询走航数据
     *
     * @param userIdParam
     * @return java.util.List<com.clear.param.output.SiteOut>
     * @author 3Clear1
     * @date 2020/8/17 18:53
     **/
    List<SiteOut> queryUserSite(UserIdParam userIdParam);
}