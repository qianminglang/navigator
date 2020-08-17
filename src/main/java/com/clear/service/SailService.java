package com.clear.service;

import com.clear.entity.Sail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocInfoOut;

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
     * 查询voc数据
     * @author 3Clear1
     * @date 2020/8/17 18:35
      * @param vocParam
     * @return com.clear.param.output.VocInfoOut
     **/
    VocInfoOut queryVocData(VocParam vocParam);

    /**
     * 查询用户下的车
     * @author 3Clear1
     * @date 2020/8/17 18:52
      * @param userIdParam
     * @return java.util.List<com.clear.param.output.SiteOut>
     **/
    List<SiteOut> queryUserSite(UserIdParam userIdParam);
}
