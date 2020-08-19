package com.clear.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clear.entity.Sail;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocHistoryParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
public interface SailService extends IService<Sail> {
    /**
     * 查询用户下的车
     * @author 3Clear1
     * @date 2020/8/17 18:52
      * @param userIdParam
     * @return java.util.List<com.clear.param.output.SiteOut>
     **/
    List<SiteOut> queryUserSite(UserIdParam userIdParam);

    /**
     * 查询voc实时数据
     * @author 3Clear1
     * @date 2020/8/17 18:35
     * @param vocParam
     * @return com.clear.param.output.VocInfoOut
     **/
    VocRealTimeInfoOut queryRealTimeVocData(VocParam vocParam);

    /**
     *查询voc历史数据
     * @author 3Clear1
     * @date 2020/8/18 16:56
      * @param vocParam
     * @return com.clear.param.output.VocInfoOut
     **/
    List<VocHistoryInfoOut> queryHistoryVocData(VocParam vocParam);

    /**
     * 查询voc历史走航列表
     * @author 3Clear1
     * @date 2020/8/18 14:30
      * @param vocHistoryParam
     * @return java.util.List<com.clear.param.output.VocHistoryOut>
     **/
    List<VocHistoryOut> queryHistoryList(VocHistoryParam vocHistoryParam);

    /**
     * 查询voc计算结果，查询data_derived表
     * @author 3Clear1
     * @date 2020/8/19 11:25
      * @param vocParam
     * @return com.clear.param.output.VocComposeInfoOut
     **/
    VocComposeInfoOut queryComposeVocDate(VocParam vocParam);
}
