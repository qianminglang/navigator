package com.clear.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.clear.entity.InformationTable;
import com.clear.param.Response;
import com.clear.param.input.UserInfoParam;
import com.clear.service.InformationTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/informationTable")
public class InformationTableController {
    @Autowired
    private InformationTableService informationTableService;

    @RequestMapping("/selectByPage")
    public Response<IPage<InformationTable>> selectByPage(@RequestBody UserInfoParam userInfoParam) {
        userInfoParam.checkInput();
        return new Response<>(informationTableService.selectUserInfoByPage(userInfoParam));
    }
}
