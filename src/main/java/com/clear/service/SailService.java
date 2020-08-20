package com.clear.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clear.entity.Sail;
import com.clear.param.input.BusParam;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocHistoryParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.*;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
public interface SailService extends IService<Sail> {
    /**
     * 查询用户下的车
     *
     * @param userIdParam
     * @return java.util.List<com.clear.param.output.SiteOut>
     * @author 3Clear1
     * @date 2020/8/17 18:52
     **/
    List<SiteOut> queryUserSite(UserIdParam userIdParam);

    /**
     * 查询voc实时数据
     *
     * @param vocParam
     * @return com.clear.param.output.VocInfoOut
     * @author 3Clear1
     * @date 2020/8/17 18:35
     **/
    VocHisTimeInfoOut queryHisVocData(VocParam vocParam);

    /**
     * 查询voc历史数据
     *
     * @param vocParam
     * @return com.clear.param.output.VocInfoOut
     * @author 3Clear1
     * @date 2020/8/18 16:56
     **/
    List<VocRealInfoOut> queryRealVocData(VocParam vocParam);

    /**
     * 查询voc历史走航列表
     *
     * @param vocHistoryParam
     * @return java.util.List<com.clear.param.output.VocHistoryOut>
     * @author 3Clear1
     * @date 2020/8/18 14:30
     **/
    List<VocHistoryOut> queryHistoryList(VocHistoryParam vocHistoryParam);

    /**
     * 查询voc计算结果，查询data_derived表
     *
     * @param vocParam
     * @return com.clear.param.output.VocComposeInfoOut
     * @author 3Clear1
     * @date 2020/8/19 11:25
     **/
    VocComposeInfoOut queryComposeVocDate(VocParam vocParam);

    /**
     * 查询当前车最新任务的状态
     *
     * @param busParam
     * @return com.clear.param.output.LatestBusInfoOut
     * @author 3Clear1
     * @date 2020/8/20 13:50
     **/
    LatestBusInfoOut queryLatestBusStatus(BusParam busParam);
}
