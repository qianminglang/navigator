package com.clear.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clear.entity.InformationTable;
import com.clear.mapper.InformationTableMapper;
import com.clear.param.input.UserInfoParam;
import com.clear.service.InformationTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-14
 */
@Service
public class InformationTableServiceImpl extends ServiceImpl<InformationTableMapper, InformationTable> implements InformationTableService {

    @Resource
    private InformationTableMapper informationTableMapper;

    @Override
    public IPage<InformationTable> selectUserInfoByPage(UserInfoParam userInfoParam) {
        Page<InformationTable> informationTablePage = new Page<>(userInfoParam.getPageNo(), userInfoParam.getPageSize());
        return informationTableMapper.selectUserInfoByPage(informationTablePage,userInfoParam);
    }
}
