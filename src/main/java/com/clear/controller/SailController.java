package com.clear.controller;


import com.clear.param.Response;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocHistoryParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryInfoOut;
import com.clear.param.output.VocHistoryOut;
import com.clear.param.output.VocRealTimeInfoOut;
import com.clear.service.SailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
     *
     * @param userIdParam
     * @return com.clear.param.Response<com.clear.param.output.VocInfoOut>
     * @author 3Clear1
     * @date 2020/8/17 18:44
     **/
    @PostMapping("/userSite")
    @ResponseBody
    public Response<List<SiteOut>> queryUserSite(@RequestBody UserIdParam userIdParam) throws JsonProcessingException {
        List<SiteOut> siteOutList = sailService.queryUserSite(userIdParam);
        return Response.SUCCESS(siteOutList);
    }

    /**
     * 查询车下面的实时走航数据
     *
     * @param vocParam
     * @return com.clear.param.Response<com.clear.param.output.VocInfoOut>
     * @author 3Clear1
     * @date 2020/8/17 18:45
     **/
    @PostMapping("/queryRealTimeVocData")
    @ResponseBody
    public Response<VocRealTimeInfoOut> queryRealTimeVocData(@RequestBody VocParam vocParam) throws JsonProcessingException {
        VocRealTimeInfoOut vocInfoOut = sailService.queryRealTimeVocData(vocParam);
        return Response.SUCCESS(vocInfoOut);
    }

    /**
     * 查询车下面的实时历史数据
     *
     * @param vocParam
     * @return com.clear.param.Response<com.clear.param.output.VocInfoOut>
     * @author 3Clear1
     * @date 2020/8/17 18:45
     **/
    @PostMapping("/queryHistoryVocData")
    @ResponseBody
    public Response<List<VocHistoryInfoOut>> queryHistoryVocData(@RequestBody VocParam vocParam) throws JsonProcessingException {
        List<VocHistoryInfoOut> vocInfoOut = sailService.queryHistoryVocData(vocParam);
        return Response.SUCCESS(vocInfoOut);
    }


    /**
     * 查询voc历史走航列表
     * @author 3Clear1
     * @date 2020/8/18 14:31
      * @param vocHistoryParam
     * @return com.clear.param.Response<java.util.List<com.clear.param.output.VocHistoryOut>>
     **/
    @PostMapping("/queryHistoryList")
    @ResponseBody
    public Response<List<VocHistoryOut>> queryHistoryList(@RequestBody VocHistoryParam vocHistoryParam) throws JsonProcessingException {
        List<VocHistoryOut> vocHistoryOutList = sailService.queryHistoryList(vocHistoryParam);
        return Response.SUCCESS(vocHistoryOutList);
    }
}
