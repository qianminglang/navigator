package com.clear.controller;


import com.clear.param.Response;
import com.clear.param.input.BusParam;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocHistoryParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.*;
import com.clear.service.SailService;
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
    public Response<List<SiteOut>> queryUserSite(@RequestBody UserIdParam userIdParam) {
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
    @PostMapping("/queryHistoryVocData")
    @ResponseBody
    public Response<VocHisTimeInfoOut> queryHistoryVocData(@RequestBody VocParam vocParam) {
        VocHisTimeInfoOut vocInfoOut = sailService.queryHisVocData(vocParam);
        return Response.SUCCESS(vocInfoOut);
    }


    /**
     * 查询voc历史走航列表
     *
     * @param vocHistoryParam
     * @return com.clear.param.Response<java.util.List < com.clear.param.output.VocHistoryOut>>
     * @author 3Clear1
     * @date 2020/8/18 14:31
     **/
    @PostMapping("/queryHistoryList")
    @ResponseBody
    public Response<List<VocHistoryOut>> queryHistoryList(@RequestBody VocHistoryParam vocHistoryParam) {
        List<VocHistoryOut> vocHistoryOutList = sailService.queryHistoryList(vocHistoryParam);
        return Response.SUCCESS(vocHistoryOutList);
    }

    /**
     * 根据车id查询当前车最新任务的状态
     *
     * @param busParam
     * @return com.clear.param.Response<java.lang.Object>
     * @author 3Clear1
     * @date 2020/8/20 13:47
     **/
    @PostMapping("/queryLatestBusStatus")
    @ResponseBody
    public Response<LatestBusInfoOut> queryLatestBusStatus(@RequestBody BusParam busParam) {
        LatestBusInfoOut latestBusInfoOut = sailService.queryLatestBusStatus(busParam);
        return Response.SUCCESS(latestBusInfoOut);
    }
}
