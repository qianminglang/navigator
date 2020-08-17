package com.clear.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clear.entity.Sail;
import com.clear.mapper.SailMapper;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocInfoOut;
import com.clear.repository.VocRepository;
import com.clear.service.SailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@Service
public class SailServiceImpl extends ServiceImpl<SailMapper, Sail> implements SailService {

    @Resource
    private VocRepository vocRepository;

    @Override
    public List<SiteOut> queryUserSite(UserIdParam userIdParam) {
        List<SiteOut> siteOuts=vocRepository.queryUserSite(userIdParam);
        return siteOuts;
    }

    @Override
    public VocInfoOut queryVocData(VocParam vocParam) {
        VocInfoOut vocInfoOut=vocRepository.queryVocData(vocParam);
        return vocInfoOut;
    }
}
