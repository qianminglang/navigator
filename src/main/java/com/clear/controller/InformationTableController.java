package com.clear.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.clear.entity.InformationTable;
import com.clear.param.Response;
import com.clear.param.input.UserInfoParam;
import com.clear.param.output.ItemInfoOut;
import com.clear.service.InformationTableService;
import com.clear.service.SailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
@Api(tags = "废弃")
public class InformationTableController {
    @Resource
    private InformationTableService informationTableService;

    @Resource
    private SailService sailService;

    @PostMapping("/selectByPage")
    @ApiOperation("废弃")
    public Response<IPage<InformationTable>> selectByPage(@RequestBody UserInfoParam userInfoParam) {
        userInfoParam.checkInput();
        return new Response<>(informationTableService.selectUserInfoByPage(userInfoParam));
    }

    @ApiOperation("生成item.json")
    @PostMapping("/generatorItem")
    public Response<List<ItemInfoOut>> generatorItem() {
        List<ItemInfoOut> itemInfoOuts = sailService.generatorItem();
        return Response.SUCCESS(itemInfoOuts);
    }
}
