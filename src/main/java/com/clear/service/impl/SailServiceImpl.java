package com.clear.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clear.consts.Constants;
import com.clear.converter.input.SailInfoConverter;
import com.clear.entity.*;
import com.clear.exception.ClearArgumentException;
import com.clear.mapper.SailMapper;
import com.clear.param.input.BusParam;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocHistoryParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.Param.CurTask;
import com.clear.param.output.Param.Equ;
import com.clear.param.output.Param.Item;
import com.clear.param.output.*;
import com.clear.paramtemp.SailParamTemp;
import com.clear.paramtemp.VocDerviedTemp;
import com.clear.paramtemp.VocTemp;
import com.clear.repository.UserInfoRepository;
import com.clear.repository.VocRepository;
import com.clear.service.SailService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private static final Map<String, String> staticMap = new LinkedHashMap();
    private static final Map<Integer, String> staticDerivedMap = new LinkedHashMap();
    private static final Map EnStaticMap = new LinkedHashMap();
    private static final DateTimeFormatter dateTimeFormatter = Constants.dateTimeFormatter;

    private static final List initTypeList = Arrays.asList(new Float[]{-999f, -999f, -999f, -999f});

    @Resource
    private SailInfoConverter sailInfoConverter;

    @Resource
    private VocRepository vocRepository;

    @Resource
    private UserInfoRepository userInfoRepository;

    static {
//        衍生数据类型：0-最大voc、1-TVOC、2-最大OFP、3-最大SOAP、4-OFP、5-SOAP、6-OFP总和、7-SOAP总和
        staticDerivedMap.put(0, "最大voc");
        staticDerivedMap.put(1, "TVOC");
        staticDerivedMap.put(2, "最大OFP");
        staticDerivedMap.put(3, "最大SOAP");
        staticDerivedMap.put(4, "OFP");
        staticDerivedMap.put(5, "SOAP");
        staticDerivedMap.put(6, "OFP总和");
        staticDerivedMap.put(7, "SOAP总和");


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


    @Override
    public List<SiteOut> queryUserSite(UserIdParam userIdParam) {
        List<String> stationCodeS = vocRepository.queryUserSite(userIdParam).stream().distinct().collect(Collectors.toList());
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

        //下面代码的逻辑就是根据车分组取出走航任务结束时间最新的一条数据
        //优先取结束时间为空的（走航任务还没有结束），如果结束时间为空的有多条则取id最大的一条（数据库查询的时候有倒序）
        //结束时间不为空则任意取一条
        //不存在走航任务则curTask返回null
        if (CollectionUtils.isNotEmpty(siteStatus)) {
            for (SiteOut siteOut : siteOuts) {
                String id = siteOut.getId();
                //1.先筛选相同id的数据
                List<Sail> ids = siteStatus.stream().filter(item -> id.equals(item.getStationId())).collect(Collectors.toList());
                Sail sail = null;
                LocalDateTime startTime = null;
                LocalDateTime endTime = null;
                String endUserId = null;
                String startUserId = null;
                //车id下存在走航任务
                if (CollectionUtils.isNotEmpty(ids)) {
                    //2.再筛选结束时间为空的数据
                    List<Sail> endTimeNull = ids.stream().filter(item -> null == item.getEndTime()).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(endTimeNull)) {
                        //当数据不唯一则任意取一条
                        sail = endTimeNull.get(0);
                    } else {
                        sail = ids.get(0);
                    }
                    startTime = sail.getStartTime();
                    endTime = sail.getEndTime();
                    endUserId = sail.getEndUserId();
                    startUserId = sail.getStartUserId();
                }

                siteOut.setStartTime(startTime);
                siteOut.setEndTime(endTime);
                siteOut.setEndUserId(endUserId);
                siteOut.setStartUserId(startUserId);
                //sailing 1表示在走航中
                Integer sailing = 0;
                //
                if (Objects.nonNull(sail) && Objects.isNull(endTime)) {
                    endTime = LocalDateTime.now();
                    sailing = 1;
                }
                CurTask curTask = null;
                if (Objects.nonNull(sail)) {
                    curTask = CurTask.builder()
                            .timeS(sail.getStartTime())
                            .timeE(endTime)
                            .id(sail.getSailId().toString())
                            .build();
                }
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
    public VocHisTimeInfoOut queryHisVocData(VocParam vocParam) {
        //根据走航车编码查询voc的仪器编码，查询的是instrument表
        Instrument vocInstrument = Instrument.builder()
                .stationcode(vocParam.getStationCode())
                .model(Constants.VOC)
                .build();
        Instrument vocInstrumentOutPut = getInstrument(vocInstrument);
        if (Objects.isNull(vocInstrumentOutPut)) {
            throw new ClearArgumentException("导航车查询的Voc设备为空");
        }

        //根据走航车编码查询GPS的仪器编码，查询的是instrument表
        Instrument gpsInstrument = Instrument.builder()
                .stationcode(vocParam.getStationCode())
                .model(Constants.GPS)
                .build();
        Instrument gpsInstrumentOutPut = getInstrument(gpsInstrument);
        if (Objects.isNull(gpsInstrumentOutPut)) {
            throw new ClearArgumentException("导航车查询的GPS设备为空");
        }

        //只取了第一个的仪器id和频率id
        //此处取出的是voc和gps的仪器id
        Integer vocInstrumentid = vocInstrumentOutPut.getInstrumentid();


        //根据仪器仪器编码去查询因子id,查询的是instrumentparameters表
        List<Integer> parameters = getParameters(vocInstrumentid);
        //当因子小于2的时候,除了经纬度就不会包含其他元素了，直接抛出异常
        if (parameters.size() <= Constants.INTEGER_2) {
            throw new ClearArgumentException("导航车查询的元素因子少于2");
        }

        //查询voc数据
        VocTemp vocTemp = sailInfoConverter.vocParamConvert(vocParam);
        Integer vocDurationid = vocInstrumentOutPut.getDurationid();
        ArrayList<Integer> instrumentids = new ArrayList<>();
        //查询因子数据既要查询voc，也要查询gps（经纬度）
        instrumentids.add(vocInstrumentid);
        instrumentids.add(gpsInstrumentOutPut.getInstrumentid());
        vocTemp.setInstrumentids(instrumentids);
        vocTemp.setDurationid(vocDurationid);
        List<Data> dataList = getVocData(vocTemp);

        //查询TvOC等四个衍生数据
        VocDerviedTemp vocDerviedTemp = VocDerviedTemp.builder()
                .durationid(vocDurationid)
                .instrumentid(vocInstrumentid)
                .startTime(vocTemp.getStartTime())
                .endTime(vocTemp.getEndTime())
                .build();
        List<DataDerived> dataDeriveds = getDerivedsVocData(vocDerviedTemp);
        Map<Long, List<DataDerived>> dataDerivedMap = dataDeriveds.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));

        //查询data_voc表，计算因子mg/立方米数据
        List<DataVoc> ugm3Data = getUgm3Data(vocTemp);
        Map<Long, List<DataVoc>> ugm3Map = ugm3Data.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));

        //初始化结果
        List<Long> timeAry = new LinkedList<>();
        List<List<Float>> ptAry = new LinkedList<>();
        List<List<Float>> dataAryPVB = new LinkedList<>();
        List<List<Float>> dataAryUgm3 = new LinkedList<>();
        List<List<Float>> dataAryZhenDuan = new LinkedList<>();

        Map<Long, List<Data>> dateTimeListMap = dataList.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));
        dateTimeListMap = sortByKey(dateTimeListMap, false);
        for (Map.Entry<Long, List<Data>> longListEntry : dateTimeListMap.entrySet()) {
            Long time = longListEntry.getKey();
            List<Float> lonLatList = new LinkedList<>();
            List<Float> pvbList = new LinkedList<>();
            List<Float> ugm3List = new LinkedList<>();
            List<Float> zhenDuanList = new LinkedList<>();
            List<Data> groupData = dateTimeListMap.get(time);

            //根据每个时间点封装data（因子普通数据，单位pvb）表中数据,和data_voc（因子普通数据，单位毫克/立方米）的数据
            //根据每个时间点封装data_voc的数据
            List<DataVoc> oneTimeUgm3Data = ugm3Map.get(time);
            getParameterResult(parameters, groupData, lonLatList, pvbList, oneTimeUgm3Data, ugm3List);

            //根据每个时间点封装data_derived（诊断量）表中数据
            List<DataDerived> dataDerivedOneTime = dataDerivedMap.get(time);
            if (CollectionUtils.isNotEmpty(dataDerivedOneTime)) {
                //根据type排序
//                dataDerivedOneTime.sort(Comparator.comparing(DataDerived::getType));
                Map<Integer, DataDerived> derivedTypeMap = dataDerivedOneTime.stream().collect(Collectors.toMap(DataDerived::getType, e -> e, (oldValue, newValue) -> newValue));
                for (Integer key : staticDerivedMap.keySet()) {
                    DataDerived dataDerived = derivedTypeMap.get(key);
                    if (Objects.nonNull(dataDerived)) {
                        zhenDuanList.add(Optional.ofNullable(dataDerived.getValue()).orElse(new BigDecimal(-999)).floatValue());
                    } else {
                        zhenDuanList.add(-999f);
                    }
                }
//                //只需要取出排序后的value值,如果是null就置为-999
//                zhenDuanList = dataDerivedOneTime.stream().map(e -> Optional.ofNullable(e.getValue()).orElse(new BigDecimal(-999)).floatValue()).collect(Collectors.toList());
            }
            if (Objects.nonNull(lonLatList)) {
                lonLatList.add(0f);
                timeAry.add(time);
                ptAry.add(lonLatList);
                dataAryPVB.add(pvbList);
                dataAryUgm3.add(ugm3List);
                dataAryZhenDuan.add(zhenDuanList);
            }
        }
        VocHisTimeInfoOut vocInfoOut = VocHisTimeInfoOut.builder()
                .timeAry(timeAry)
                .ptAry(ptAry)
                .dataAryPVB(dataAryPVB)
                .dataAryUgm3(dataAryUgm3)
                .dataAryZhenDuan(dataAryZhenDuan)
                .build();
        return vocInfoOut;
    }

    private List<DataVoc> getUgm3Data(VocTemp vocTemp) {
        return vocRepository.getUgm3Data(vocTemp);
    }

    /**
     * 查询voc数据
     *
     * @param vocTemp
     * @return java.util.List<com.clear.entity.Data>
     * @author 3Clear1
     * @date 2020/8/19 12:17
     **/
    private List<Data> getVocData(VocTemp vocTemp) {
        return vocRepository.queryVocData(vocTemp);
    }

    /**
     * 根据走航车编码查询voc的仪器编码，查询的是instrument表
     *
     * @param instrument
     * @return java.util.List<com.clear.entity.Instrument>
     * @author 3Clear1
     * @date 2020/8/19 12:16
     **/
    private Instrument getInstrument(Instrument instrument) {
        return vocRepository.queryInstrument(instrument);
    }

    @Override
    public List<VocRealInfoOut> queryRealVocData(VocParam vocParam) {
        //根据走航车编码查询voc的仪器编码，查询的是instrument表
        Instrument vocInstrument = Instrument.builder()
                .stationcode(vocParam.getStationCode())
                .model(Constants.VOC)
                .build();
        Instrument vocInstrumentOut = getInstrument(vocInstrument);
        if (Objects.isNull(vocInstrumentOut)) {
            throw new ClearArgumentException("导航车查询的Voc设备为空");
        }

        //根据走航车编码查询voc的仪器编码，查询的是instrument表
        Instrument gpsInstrument = Instrument.builder()
                .stationcode(vocParam.getStationCode())
                .model(Constants.GPS)
                .build();
        Instrument gpsInstrumentOut = getInstrument(gpsInstrument);
        if (Objects.isNull(gpsInstrumentOut)) {
            throw new ClearArgumentException("导航车查询的GPS设备为空");
        }

        //只取了第一个的仪器id和频率id
        //此处取出的是voc和gps的仪器id
        Integer vocInstrumentid = vocInstrumentOut.getInstrumentid();


        //根据仪器仪器编码去查询因子id,查询的是instrumentparameters表
        List<Integer> parameters = getParameters(vocInstrumentid);

        //查询voc数据
        VocTemp vocTemp = sailInfoConverter.vocParamConvert(vocParam);
        Integer durationid = vocInstrumentOut.getDurationid();
        ArrayList<Integer> instrumentids = new ArrayList<>();
        //查询因子数据既要查询voc，也要查询gps（经纬度）
        instrumentids.add(vocInstrumentid);
        instrumentids.add(gpsInstrumentOut.getInstrumentid());
        vocTemp.setDurationid(durationid);
        vocTemp.setInstrumentids(instrumentids);
        List<Data> dataList = getVocData(vocTemp);

        //初始化结果
        List<LocalDateTime> lsAry = new LinkedList<>();
        for (Data data : dataList) {
            LocalDateTime lst = data.getLst();
            lsAry.add(lst);
        }
        //时间去重
        lsAry = lsAry.stream().distinct().collect(Collectors.toList());
        Map<LocalDateTime, List<Data>> dateTimeListMap = dataList.stream().collect(Collectors.groupingBy(Data::getLst));
        LinkedList<VocRealInfoOut> resultList = new LinkedList<>();
        for (LocalDateTime ls : lsAry) {
            VocRealInfoOut vocHistoryInfoOut = new VocRealInfoOut();
            List<Data> groupData = dateTimeListMap.get(ls);
            LinkedList<Float> lonLatList = new LinkedList<>();
            LinkedList<Float> parameterValueList = new LinkedList<>();
            Long aLong = Long.valueOf(dateTimeFormatter.format(ls));
//            getParameterResult(parameters, groupData, lonLatList, parameterValueList);
            lonLatList.add(0f);
            vocHistoryInfoOut.setTime(aLong);
            vocHistoryInfoOut.setDataAry(parameterValueList);
            vocHistoryInfoOut.setPt(lonLatList);
            resultList.add(vocHistoryInfoOut);
        }
        return resultList;
    }

    /**
     * 根据仪器仪器编码去查询因子id,查询的是instrumentparameters表
     *
     * @param instrumentid
     * @return java.util.List<java.lang.Integer>
     * @author 3Clear1
     * @date 2020/8/19 12:16
     **/
    private List<Integer> getParameters(Integer instrumentid) {
        //根据仪器仪器编码去查询因子id,查询的是instrumentparameters表
        return vocRepository.queryInstrumentParameter(instrumentid);
    }

    /**
     * 封装从data表中查询出来的数据
     *
     * @param parameters
     * @param groupData
     * @param lonLatList
     * @param pvbList
     * @return void
     * @author 3Clear1
     * @date 2020/8/20 9:35
     **/
    private void getParameterResult(List<Integer> parameters, List<Data> groupData, List<Float> lonLatList, List<Float> pvbList, List<DataVoc> oneTimeUgm3Data, List<Float> ugm3List) {
        //当经纬度有一个不存在的时候lonLatList就置位null，方法接受到lonLatList为null的时候则移除当前时间的数据
        groupData.sort(Comparator.comparing(Data::getParameterid));
        Map<Integer, DataVoc> Ugm3DataMap = new HashMap<>();
        if (Objects.nonNull(oneTimeUgm3Data)) {
            Ugm3DataMap = oneTimeUgm3Data.stream().collect(Collectors.toMap(DataVoc::getParameterid, e -> e, (oldValue, newValue) -> newValue));
        }
        Map<Integer, Data> dataMap = groupData.stream().collect(Collectors.toMap(Data::getParameterid, e -> e, (oldValue, newValue) -> newValue));
        for (String key : staticMap.keySet()) {
            DataVoc Ugm3Data = Ugm3DataMap.get(Integer.valueOf(key));
            Data data = dataMap.get(Integer.valueOf(key));
            if (Constants.INTEGER_31.equals(Integer.valueOf(key))) {
                if (Objects.nonNull(data)) {
                    lonLatList.add(data.getValue().floatValue());
                } else {
                    lonLatList = null;
                }
            } else if (Constants.INTEGER_32.equals(Integer.valueOf(key))) {
                if (Objects.nonNull(data)) {
                    lonLatList.add(data.getValue().floatValue());
                } else {
                    lonLatList = null;
                }
            } else {
                if (Objects.nonNull(data)) {
                    pvbList.add(Optional.ofNullable(data.getValue()).orElse(new BigDecimal(-999)).floatValue());
                } else {
                    pvbList.add(-999f);
                }
                if (Objects.nonNull(Ugm3Data)) {
                    ugm3List.add(Optional.ofNullable(Ugm3Data.getValue()).orElse(new BigDecimal(-999)).floatValue());
                } else {
                    ugm3List.add(-999f);
                }
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

    @Override
    public VocComposeInfoOut queryComposeVocDate(VocParam vocParam) {
        Instrument gpsInstrument = Instrument.builder()
                .stationcode(vocParam.getStationCode())
                .model(Constants.GPS)
                .build();
        Instrument gpsInstrumentOut = getInstrument(gpsInstrument);
        if (Objects.isNull(gpsInstrumentOut)) {
            throw new ClearArgumentException("导航车查询的设备为空");
        }
        //只取了第一个的仪器id和频率id
        //此处取出的是voc的仪器id
        Integer instrumentId = gpsInstrumentOut.getInstrumentid();

        //查询voc数据
        VocTemp vocTemp = sailInfoConverter.vocParamConvert(vocParam);
        Integer gpsDurationId = gpsInstrumentOut.getDurationid();
        ArrayList<Integer> instrumentids = new ArrayList<>();
        //只查询gps（经纬度）
        instrumentids.add(Constants.INTEGER_5);
        vocTemp.setInstrumentids(instrumentids);
        vocTemp.setDurationid(gpsDurationId);
        List<Data> dataList = getVocData(vocTemp);
        Map<Long, List<Data>> dataMap = dataList.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));

        //查询TvOC等四个衍生数据
        List<DataDerived> dataDeriveds = null;
        Map<Long, List<DataDerived>> dataDerivedMap = dataDeriveds.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));
        //排序
        dataDerivedMap = sortByKey(dataDerivedMap, false);
        //初始化结果
        List<Long> timeAry = new LinkedList<>();
        List<List<Float>> ptAry = new LinkedList<>();
        List<List<Float>> comAry = new LinkedList<>();

        for (Map.Entry<Long, List<DataDerived>> oneDataDerivedMap : dataDerivedMap.entrySet()) {
            Long key = oneDataDerivedMap.getKey();
            List<DataDerived> value = oneDataDerivedMap.getValue();
            //按照type排序
            value.sort(Comparator.comparing(DataDerived::getType));

            //取到data表中相同时间点的经纬度信息
            List<Data> lonlatList = dataMap.get(key);
            //只有当经纬度都存在的时候才会保留当前的时间数据
            if (CollectionUtils.isNotEmpty(lonlatList) && lonlatList.size() >= Constants.INTEGER_2) {

            }

            List<Float> lonLatList = new LinkedList<>();
            List<Float> parameterValueList = new LinkedList<>();

            lonLatList.add(0f);
            ptAry.add(lonLatList);
            comAry.add(parameterValueList);
        }
        VocComposeInfoOut vocComposeInfoOut = VocComposeInfoOut.builder()
                .timeAry(timeAry)
                .ptAry(ptAry)
                .comAry(comAry)
                .build();
        return vocComposeInfoOut;
    }

    @Override
    public LatestBusInfoOut queryLatestBusStatus(BusParam busParam) {
        //根据车查询任务信息
        List<Sail> sails = vocRepository.querySailStatus(Arrays.asList(busParam.getStationCode()));
        Sail sail = null;
        //sailing 1表示在走航中,0表示结束走航，null表示没有行走记录
        Integer sailing = null;
        if (CollectionUtils.isNotEmpty(sails)) {
            //首先筛选endTime为空的数据，为空的就默认取结束时间为空的第一个
            List<Sail> noEndTimes = sails.stream().filter(item -> Objects.isNull(item.getEndTime())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(noEndTimes)) {
                sail = noEndTimes.get(0);
                sailing = 1;
            } else {
                sail = sails.get(0);
                sailing = 0;
            }
        }
        LatestBusInfoOut latestBusInfoOut = sailInfoConverter.busInfoVonvert(sail);
        latestBusInfoOut.setSailing(sailing);
        return latestBusInfoOut;
    }

    /**
     * 查询voc衍生数据，表data_derived
     *
     * @param vocDerviedTemp
     * @return java.util.List<com.clear.entity.DataDerived>
     * @author 3Clear1
     * @date 2020/8/19 13:41
     **/
    private List<DataDerived> getDerivedsVocData(VocDerviedTemp vocDerviedTemp) {
        return vocRepository.queryDerivedsVocData(vocDerviedTemp);
    }

    /**
     * 根据map的key排序
     *
     * @param map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return java.util.Map<K, V>
     * @author 3Clear1
     * @date 2020/8/19 17:46
     **/
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }
}
