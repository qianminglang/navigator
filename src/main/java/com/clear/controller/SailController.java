package com.clear.controller;


import com.clear.param.Response;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocInfoOut;
import com.clear.service.SailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/sail")
public class SailController {

    @Resource
    private SailService sailService;

    /**
     * 查询用户名下有哪些车
     * @author 3Clear1
     * @date 2020/8/17 18:44
      * @param userIdParam
     * @return com.clear.param.Response<com.clear.param.output.VocInfoOut>
     **/
    @PostMapping("/userSite")
    @ResponseBody
    public Response<List<SiteOut>> queryUserSite(@RequestBody UserIdParam userIdParam) throws JsonProcessingException {
        List<SiteOut> siteOutList = sailService.queryUserSite(userIdParam);
        return new Response<>(siteOutList);
    }

    /**
     * 查询车下面的走航数据
     * @author 3Clear1
     * @date 2020/8/17 18:45
      * @param vocParam
     * @return com.clear.param.Response<com.clear.param.output.VocInfoOut>
     **/
    @PostMapping("/queryVocData")
    @ResponseBody
    public Response<VocInfoOut> queryVocData(@RequestBody VocParam vocParam) throws JsonProcessingException {
        VocInfoOut vocInfoOut = sailService.queryVocData(vocParam);
        return  Response.SUCCESS(vocInfoOut);
    }

}
