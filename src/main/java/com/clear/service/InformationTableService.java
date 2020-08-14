package com.clear.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.clear.entity.InformationTable;
import com.clear.param.input.UserInfoParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-14
 */
public interface InformationTableService extends IService<InformationTable> {

    IPage<InformationTable> selectUserInfoByPage(UserInfoParam userInfoParam);
}
