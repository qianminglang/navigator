package com.clear.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clear.consts.Constants;
import com.clear.converter.input.SailInfoConverter;
import com.clear.entity.Data;
import com.clear.entity.Instrument;
import com.clear.entity.Sail;
import com.clear.entity.SysUser;
import com.clear.exception.ClearArgumentException;
import com.clear.mapper.SailMapper;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocHistoryParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.Param.CurTask;
import com.clear.param.output.Param.Equ;
import com.clear.param.output.Param.Item;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocHistoryInfoOut;
import com.clear.param.output.VocHistoryOut;
import com.clear.param.output.VocRealTimeInfoOut;
import com.clear.paramtemp.SailParamTemp;
import com.clear.paramtemp.VocTemp;
import com.clear.repository.UserInfoRepository;
import com.clear.repository.VocRepository;
import com.clear.service.SailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mybatis-plus generator
 * @since 2020-08-17
 */
@Service
public class SailServiceImpl extends ServiceImpl<SailMapper, Sail> implements SailService {
    private static final Map staticMap = new LinkedHashMap();
    private static final Map EnStaticMap = new LinkedHashMap();

    static {
        staticMap.put("31", "经度");
        staticMap.put("32", "纬度");
        staticMap.put("35", "硝基苯");
        staticMap.put("41", "1,2,4-三甲苯");
        staticMap.put("44", "茚");
        staticMap.put("47", "萘");
        staticMap.put("52", "硝基甲苯");
        staticMap.put("56", "α-蒎烯");
        staticMap.put("61", "2-丁酮");
        staticMap.put("64", "异戊二烯");
        staticMap.put("72", "二甲基硫");
        staticMap.put("75", "乙醛");
        staticMap.put("76", "乙腈");
        staticMap.put("83", "苯");
        staticMap.put("87", "丙酮");
        staticMap.put("88", "甲醇");
        staticMap.put("94", "乙醇");
        staticMap.put("123", "甲醛");
        staticMap.put("124", "3-糠醛");
        staticMap.put("163", "吡啶");
        staticMap.put("174", "苯酚");
        staticMap.put("176", "甲苯");
        staticMap.put("178", "二甲苯");
        staticMap.put("186", "丙烯腈");
        staticMap.put("188", "丙烯醛");
        staticMap.put("197", "苯甲腈");
        staticMap.put("198", "苯乙烯");
        staticMap.put("203", "氯苯");
        staticMap.put("204", "4-甲基-2-戊酮");
        staticMap.put("205", "乙酸乙酯");
        staticMap.put("206", "N,N-二甲基甲酰胺");
        staticMap.put("207", "异丙醇");
        staticMap.put("208", "丙炔腈");


        EnStaticMap.put("31", "经度");
        EnStaticMap.put("32", "纬度");
        EnStaticMap.put("35", "XJB");
        EnStaticMap.put("41", "SJB");
        EnStaticMap.put("44", "YIN");
        EnStaticMap.put("47", "NAI");
        EnStaticMap.put("52", "XJJB");
        EnStaticMap.put("56", "PX");
        EnStaticMap.put("61", "DT");
        EnStaticMap.put("64", "YWEX");
        EnStaticMap.put("72", "EJJL");
        EnStaticMap.put("75", "YQUAN");
        EnStaticMap.put("76", "YQ");
        EnStaticMap.put("83", "BEN");
        EnStaticMap.put("87", "BT");
        EnStaticMap.put("88", "JC");
        EnStaticMap.put("94", "YCH");
        EnStaticMap.put("123", "JQ");
        EnStaticMap.put("124", "TQ");
        EnStaticMap.put("163", "CD");
        EnStaticMap.put("174", "BF");
        EnStaticMap.put("176", "JBEN");
        EnStaticMap.put("178", "EJB");
        EnStaticMap.put("186", "BXQ");
        EnStaticMap.put("188", "BXQQ");
        EnStaticMap.put("197", "BJQ");
        EnStaticMap.put("198", "BYX");
        EnStaticMap.put("203", "LB");
        EnStaticMap.put("204", "JJ_WT");
        EnStaticMap.put("205", "YSYZ");
        EnStaticMap.put("206", "EJJJXA");
        EnStaticMap.put("207", "YBC");
        EnStaticMap.put("208", "BQQ");


    }

    @Resource
    private SailInfoConverter sailInfoConverter;

    @Resource
    private VocRepository vocRepository;

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public List<SiteOut> queryUserSite(UserIdParam userIdParam) {
        List<String> stationCodeS = vocRepository.queryUserSite(userIdParam);
        //当查询关联表没有查询到数据，则直接返回空数据
        if (CollectionUtils.isEmpty(stationCodeS)) {
            return Collections.emptyList();
        }
        //查询走航车名称
        List<SiteOut> siteOuts = vocRepository.queryUserSiteDetail(stationCodeS);
        if (CollectionUtils.isEmpty(siteOuts)) {
            return Collections.emptyList();
        }


        LinkedList<Item> items = new LinkedList<>();
        //模拟数据局
        EnStaticMap.forEach((key, value) -> {
            if (key.equals(Constants.INTEGER_31.toString()) || key.equals(Constants.INTEGER_32.toString())) {
                return;
            }
            Item item = Item.builder().id(value.toString()).show(1).build();
            items.add(item);
        });
        Equ equ = Equ.builder().equID("VOC").itemAry(items).build();

        //查询走航车的状态，取每个走航车最新的状态
        List<Sail> siteStatus = vocRepository.querySailStatus(stationCodeS);
        if (CollectionUtils.isNotEmpty(siteStatus)) {
            Map<String, Sail> sailMap = siteStatus.stream().collect(Collectors.toMap(Sail::getStationId, e -> e, (oldValue, newValue) -> newValue));
            for (SiteOut siteOut : siteOuts) {
                Sail sail = sailMap.get(siteOut.getId());
                siteOut.setStartTime(sail.getStartTime());
                siteOut.setEndTime(sail.getEndTime());
                siteOut.setEndUserId(sail.getEndUserId());
                siteOut.setStartUserId(sail.getStartUserId());
                LocalDateTime endTime = sail.getEndTime();
                //sailing 1表示在走航中
                Integer sailing = 0;
                if (Objects.isNull(endTime)) {
                    endTime = LocalDateTime.now();
                    sailing = 1;
                }
                CurTask curTask = CurTask.builder()
                        .timeS(sail.getStartTime())
                        .timeE(endTime)
                        .id(sail.getSailId())
                        .build();
                siteOut.setCurTask(curTask);
                siteOut.setSailing(sailing);
                siteOut.setEquAry(Arrays.asList(equ));
                siteOut.setVedioInfo("vedioInfo");
                siteOut.setModelOffset("0");
                siteOut.setModelUrl("./GroundVehicle.glb");
                siteOut.setFreSecd("1");
            }
        }
        return siteOuts;
    }

    @Override
    public VocRealTimeInfoOut queryRealTimeVocData(VocParam vocParam) {
        VocBaseInfo vocBaseInfo = new VocBaseInfo(vocParam).invoke();
        List<Integer> parameters = vocBaseInfo.getParameters();
        List<Data> dataList = vocBaseInfo.getDataList();

        //初始化结果
        List<LocalDateTime> lsAry = new LinkedList<>();
        List<List<Float>> ptAry = new LinkedList<>();
        List<List<Float>> dataAry = new LinkedList<>();


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (Data data : dataList) {
            LocalDateTime lst = data.getLst();
            lsAry.add(lst);
        }
        //时间去重
        lsAry = lsAry.stream().distinct().collect(Collectors.toList());
        List<Long> timeAry = new LinkedList<>();
        for (LocalDateTime localDateTime : lsAry) {
            timeAry.add(Long.valueOf(dateTimeFormatter.format(localDateTime)));
        }
        Map<LocalDateTime, List<Data>> dateTimeListMap = dataList.stream().collect(Collectors.groupingBy(Data::getLst));
        for (LocalDateTime ls : lsAry) {
            LinkedList<Float> lonLatList = new LinkedList<>();
            LinkedList<Float> parameterValueList = new LinkedList<>();
            List<Data> groupData = dateTimeListMap.get(ls);
            getParameterResult(parameters, groupData, lonLatList, parameterValueList);
            lonLatList.add(0f);
            ptAry.add(lonLatList);
            dataAry.add(parameterValueList);
        }
        VocRealTimeInfoOut vocInfoOut = VocRealTimeInfoOut.builder()
                .timeAry(timeAry)
                .ptAry(ptAry)
                .dataAry(dataAry)
                .build();
        return vocInfoOut;
    }

    @Override
    public List<VocHistoryInfoOut> queryHistoryVocData(VocParam vocParam) {
        VocBaseInfo vocBaseInfo = new VocBaseInfo(vocParam).invoke();
        List<Integer> parameters = vocBaseInfo.getParameters();
        List<Data> dataList = vocBaseInfo.getDataList();

        //初始化结果
        List<LocalDateTime> lsAry = new LinkedList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (Data data : dataList) {
            LocalDateTime lst = data.getLst();
            lsAry.add(lst);
        }
        //时间去重
        lsAry = lsAry.stream().distinct().collect(Collectors.toList());
        List<Long> timeAry = new LinkedList<>();
        for (LocalDateTime localDateTime : lsAry) {
            timeAry.add(Long.valueOf(dateTimeFormatter.format(localDateTime)));
        }
        Map<LocalDateTime, List<Data>> dateTimeListMap = dataList.stream().collect(Collectors.groupingBy(Data::getLst));
        LinkedList<VocHistoryInfoOut> resultList = new LinkedList<>();
        for (LocalDateTime ls : lsAry) {
            VocHistoryInfoOut vocHistoryInfoOut = new VocHistoryInfoOut();
            List<Data> groupData = dateTimeListMap.get(ls);
            LinkedList<Float> lonLatList = new LinkedList<>();
            LinkedList<Float> parameterValueList = new LinkedList<>();
            Long aLong = Long.valueOf(dateTimeFormatter.format(ls));
            getParameterResult(parameters, groupData, lonLatList, parameterValueList);
            lonLatList.add(0f);
            vocHistoryInfoOut.setTime(aLong);
            vocHistoryInfoOut.setDataAry(parameterValueList);
            vocHistoryInfoOut.setPt(lonLatList);
            resultList.add(vocHistoryInfoOut);
        }
        return resultList;
    }

    private void getParameterResult(List<Integer> parameters, List<Data> groupData, LinkedList<Float> lonLatList, LinkedList<Float> parameterValueList) {
        groupData.sort(Comparator.comparing(Data::getParameterid));
        for (Data data : groupData) {
            Integer parameterid = data.getParameterid();
            if (!staticMap.containsKey(parameterid.toString())) {
                break;
            }
            //31表示经度
            if (Constants.INTEGER_31.equals(data.getParameterid())) {
                lonLatList.add(data.getValue().floatValue());
            }
            //32表示纬度
            if (Constants.INTEGER_32.equals(data.getParameterid())) {
                lonLatList.add(data.getValue().floatValue());
            }

            boolean trueLon = parameterid.equals(Constants.INTEGER_31);
            boolean trueLat = parameterid.equals(Constants.INTEGER_32);
            if (trueLon || trueLat) {
                continue;
            }
            boolean flag = false;
            for (Integer parameter : parameters) {
                if (parameter.equals(data.getParameterid())) {
                    parameterValueList.add(data.getValue().floatValue());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                parameterValueList.add(-999f);
            }

        }
    }

    @Override
    public List<VocHistoryOut> queryHistoryList(VocHistoryParam vocHistoryParam) {
        UserIdParam userIdParam = UserIdParam.builder()
                .userId(vocHistoryParam.getUserId())
                .build();
        List<String> stationCodeS = vocRepository.queryUserSite(userIdParam);
        if (CollectionUtils.isEmpty(stationCodeS)) {
            return Collections.emptyList();
        }

        //查询当前用户下的导航车历史数据
        SailParamTemp sailParamTemp = SailParamTemp.builder()
                .startTime(vocHistoryParam.getStartTime())
                .endTime(vocHistoryParam.getEndTime())
                .stationCodeS(stationCodeS)
                .build();
        List<VocHistoryOut> vocHistoryOutList = vocRepository.queryHistoryList(sailParamTemp);
        if (CollectionUtils.isEmpty(vocHistoryOutList)) {
            return Collections.emptyList();
        }

        List<String> startUserIds = vocHistoryOutList.stream().map(VocHistoryOut::getStartUserId).collect(Collectors.toList());
        List<String> endUserIds = vocHistoryOutList.stream().map(VocHistoryOut::getEndUserId).collect(Collectors.toList());
        startUserIds.addAll(endUserIds);
        startUserIds.stream().distinct().collect(Collectors.toList());

        List<SysUser> sysUsers = userInfoRepository.selectUserInfoUserIds(startUserIds);
        Map<String, SysUser> sysUserMap = sysUsers.stream().collect(Collectors.toMap(SysUser::getUserid, e -> e, (oldValue, newValue) -> newValue));
        for (VocHistoryOut vocHistoryOut : vocHistoryOutList) {
            SysUser startUser = sysUserMap.get(vocHistoryOut.getStartUserId());
            if (Objects.nonNull(startUser)) {
                vocHistoryOut.setStartUserName(startUser.getUsername());
            }
            SysUser endUser = sysUserMap.get(vocHistoryOut.getStartUserId());
            if (Objects.nonNull(endUser)) {
                vocHistoryOut.setEndUserName(endUser.getUsername());
            }
        }
        return vocHistoryOutList;
    }

    private class VocBaseInfo {
        private VocParam vocParam;
        private List<Integer> parameters;
        private List<Data> dataList;

        public VocBaseInfo(VocParam vocParam) {
            this.vocParam = vocParam;
        }

        public List<Integer> getParameters() {
            return parameters;
        }

        public List<Data> getDataList() {
            return dataList;
        }

        public VocBaseInfo invoke() {
            //根据走航车编码查询voc的仪器编码，查询的是instrument表
            Instrument instrument = Instrument.builder()
                    .stationcode(vocParam.getStationCode())
                    .model(Constants.VOC)
                    .build();
            List<Instrument> instrumentS = vocRepository.queryInstrument(instrument);

            VocTemp vocTemp = sailInfoConverter.vocParamConvert(vocParam);
            if (CollectionUtils.isEmpty(instrumentS)) {
                throw new ClearArgumentException("导航车查询的设备为空");
            }
            //只取了第一个的仪器id和频率id
            //此处取出的是voc的仪器id
            Integer instrumentid = instrumentS.get(0).getInstrumentid();

            //根据仪器仪器编码去查询因子id,查询的是instrumentparameters表
            parameters = vocRepository.queryInstrumentParameter(instrumentid);


            Integer durationid = instrumentS.get(0).getDurationid();
            ArrayList<Integer> instrumentids = new ArrayList<>();

            //查询因子数据既要查询voc，也要查询gps（经纬度）
            instrumentids.add(instrumentid);
            instrumentids.add(Constants.INTEGER_5);
            vocTemp.setInstrumentids(instrumentids);
            vocTemp.setDurationid(durationid);
            dataList = vocRepository.queryVocData(vocTemp);
            return this;
        }
    }
}
