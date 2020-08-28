package com.clear.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clear.consts.Constants;
import com.clear.converter.input.SailInfoConverter;
import com.clear.entity.Data;
import com.clear.entity.*;
import com.clear.exception.ClearArgumentException;
import com.clear.mapper.SailMapper;
import com.clear.param.input.*;
import com.clear.param.output.*;
import com.clear.param.output.Param.CurTask;
import com.clear.param.output.Param.Item;
import com.clear.paramtemp.*;
import com.clear.repository.UserInfoRepository;
import com.clear.repository.VocRepository;
import com.clear.service.SailService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
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
    private static final Map<Integer, String> staticDerivedMap = new LinkedHashMap();
    private static final Map EnStaticMap = new LinkedHashMap();
    private static final DateTimeFormatter dateTimeFormatter = Constants.dateTimeFormatter;

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


                //根据走航车编码查询当前导航车下的设备编号有哪些
                List<Integer> instrumentids = Collections.emptyList();
                Instrument vocInstrument = Instrument.builder()
                        .stationcode(id)
                        .build();
                List<Instrument> vocInstrumentOutPuts = getInstrument(vocInstrument);
                if (CollectionUtils.isNotEmpty(vocInstrumentOutPuts)) {
                    instrumentids = vocInstrumentOutPuts.stream().map(Instrument::getInstrumentid).collect(Collectors.toList());
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

                //查询任务下的配置因子信息
                Long sailId = sail.getSailId();
                Map<Long, List<Integer>> integerListMap = queryParametersBySailIds(Arrays.asList(sailId));


                CurTask curTask = null;
                if (Objects.nonNull(sail)) {
                    curTask = CurTask.builder()
                            .timeS(sail.getStartTime())
                            .timeE(endTime)
                            .id(sail.getSailId())
                            .itemAry(integerListMap.get(sailId))
                            .build();
                }
                siteOut.setCurTask(curTask);
                siteOut.setSailing(sailing);
                siteOut.setEquAry(instrumentids);
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
        String stationCode = vocParam.getStationCode();
        //根据走航车编码查询voc的仪器编码，查询的是instrument表
        Instrument vocInstrument = Instrument.builder()
                .stationcode(stationCode)
                .model(Constants.VOC)
                .build();
        List<Instrument> vocInstrumentOutPuts = getInstrument(vocInstrument);
        if (CollectionUtils.isEmpty(vocInstrumentOutPuts)) {
            throw new ClearArgumentException("导航车查询的Voc设备为空");
        }

        //根据走航车编码查询GPS的仪器编码，查询的是instrument表
        Instrument gpsInstrument = Instrument.builder()
                .stationcode(stationCode)
                .model(Constants.GPS)
                .build();
        List<Instrument> gpsInstrumentOutPuts = getInstrument(gpsInstrument);
        if (CollectionUtils.isEmpty(gpsInstrumentOutPuts)) {
            throw new ClearArgumentException("导航车查询的GPS设备为空");
        }

        //只取了第一个的仪器id和频率id
        //此处取出的是voc和gps的仪器id
        Integer vocInstrumentid = vocInstrumentOutPuts.get(0).getInstrumentid();


        //根据仪器仪器编码去查询因子id,查询的是instrumentparameters表
        List<Integer> parameters = getParameters(vocInstrumentid);
        //当因子小于2的时候,除了经纬度就不会包含其他元素了，直接抛出异常
        if (parameters.size() <= Constants.INTEGER_2) {
            throw new ClearArgumentException("导航车查询的元素因子少于2");
        }

        //查询voc数据
        VocTemp vocTemp = sailInfoConverter.vocParamConvert(vocParam);
        Integer vocDurationid = vocInstrumentOutPuts.get(0).getDurationid();
        ArrayList<Integer> instrumentids = new ArrayList<>();
        //查询因子数据既要查询voc，也要查询gps（经纬度）
        instrumentids.add(vocInstrumentid);
        instrumentids.add(gpsInstrumentOutPuts.get(0).getInstrumentid());
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
        //setParameterids设置为null，在数据库中表示查询所有因子
        vocTemp.setParameterids(null);
        List<DataVoc> ugm3Data = getUgm3Data(vocTemp);
        Map<Long, List<DataVoc>> ugm3Map = ugm3Data.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));

        Map<Long, List<Data>> dateTimeListMap = dataList.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));

        //查询任务下的配置因子信息
        Long sailId = vocParam.getSailId();
        Map<Long, List<Integer>> integerListMap = queryParametersBySailIds(Arrays.asList(sailId));
        List<Integer> parameterList = integerListMap.get(sailId);
        if (CollectionUtils.isEmpty(parameterList)) {
            throw new ClearArgumentException("未查询到当前任务的配置因子的数据");
        }

        //将查询到的数据封装成需要返回的结果样式
        //设置经纬度有效值
        LonLatRangeTemp lonLatRangeTemp = LonLatRangeTemp.builder()
                .startLon(Constants.FLOAT_70)
                .endLon(Constants.FLOAT_150)
                .startLat(Constants.FLOAT_0)
                .endLat(Constants.FLOAT_60)
                .build();


        VocHisTimeInfoOut vocInfoOut = getVocHisTimeInfoOut(parameterList, parameters, dataDerivedMap, ugm3Map, dateTimeListMap, lonLatRangeTemp);

        return vocInfoOut;
    }

    /**
     * 封装历史数据
     *
     * @param parameters
     * @param dataDerivedMap
     * @param ugm3Map
     * @param dateTimeListMap
     * @return com.clear.param.output.VocHisTimeInfoOut
     * @author 3Clear1
     * @date 2020/8/22 11:21
     **/
    private VocHisTimeInfoOut getVocHisTimeInfoOut(List<Integer> parameterList, List<Integer> parameters, Map<Long, List<DataDerived>> dataDerivedMap, Map<Long, List<DataVoc>> ugm3Map, Map<Long,
            List<Data>> dateTimeListMap, LonLatRangeTemp lonLatRangeTemp) {

        //因为因子中不包含经纬度和高度，但是此处遍历的时候需要加上经纬度和高度
        parameterList.addAll(Arrays.asList(Constants.INTEGER_31, Constants.INTEGER_32, Constants.INTEGER_211));

        //初始化结果
        List<Long> timeAry = new LinkedList<>();
        List<List<Float>> ptAry = new LinkedList<>();
        List<List<Float>> dataAryPVB = new LinkedList<>();
        List<List<Float>> dataAryUgm3 = new LinkedList<>();
        List<List<Float>> dataAryZhenDuan = new LinkedList<>();


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
            getParameterResult(parameterList, groupData, lonLatList, pvbList, oneTimeUgm3Data, ugm3List, lonLatRangeTemp);

            //根据每个时间点封装data_derived（诊断量）表中数据
            List<DataDerived> dataDerivedOneTime = dataDerivedMap.get(time);
            if (CollectionUtils.isNotEmpty(dataDerivedOneTime)) {
                Map<Integer, DataDerived> derivedTypeMap = dataDerivedOneTime.stream().collect(Collectors.toMap(DataDerived::getType, e -> e, (oldValue, newValue) -> newValue));
                for (Integer key : staticDerivedMap.keySet()) {
                    DataDerived dataDerived = derivedTypeMap.get(key);
                    if (Objects.nonNull(dataDerived)) {
                        zhenDuanList.add(Optional.ofNullable(dataDerived.getValue()).orElse(new BigDecimal(-999)).floatValue());
                    } else {
                        zhenDuanList.add(-999f);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(lonLatList)) {
                timeAry.add(time);
                ptAry.add(lonLatList);
                dataAryPVB.add(pvbList);
                dataAryUgm3.add(ugm3List);
                dataAryZhenDuan.add(zhenDuanList);
            }
        }


        return VocHisTimeInfoOut.builder()
                .timeAry(timeAry)
                .ptAry(ptAry)
                .dataAryPVB(dataAryPVB)
                .dataAryUgm3(dataAryUgm3)
                .dataAryZhenDuan(dataAryZhenDuan)
                .build();
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
    private List<Instrument> getInstrument(Instrument instrument) {
        return vocRepository.queryInstrument(instrument);
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
     * @param parameterList
     * @param groupData
     * @param lonLatList
     * @param pvbList
     * @return void
     * @author 3Clear1
     * @date 2020/8/20 9:35
     **/
    private void getParameterResult(List<Integer> parameterList, List<Data> groupData, List<Float> lonLatList, List<Float> pvbList, List<DataVoc> oneTimeUgm3Data,
                                    List<Float> ugm3List, LonLatRangeTemp lonLatRangeTemp) {


        Map<Integer, Data> dataMap = groupData.stream().collect(Collectors.toMap(Data::getParameterid, e -> e, (oldValue, newValue) -> newValue));
        //如果缺少经纬度直接返回
        boolean containsLonKey = dataMap.containsKey(Constants.INTEGER_31);
        boolean containsLatKey = dataMap.containsKey(Constants.INTEGER_32);
        if (!containsLonKey || !containsLatKey) {
            return;
        }

        //当经纬度有一个不存在的时候lonLatList就置位null，方法接受到lonLatList为null的时候则移除当前时间的数据
        Map<Integer, DataVoc> Ugm3DataMap = new HashMap<>();
        if (Objects.nonNull(oneTimeUgm3Data)) {
            Ugm3DataMap = oneTimeUgm3Data.stream().collect(Collectors.toMap(DataVoc::getParameterid, e -> e, (oldValue, newValue) -> newValue));
        }


        for (Integer key : parameterList) {
            DataVoc Ugm3Data = Ugm3DataMap.get(key);
            Data data = dataMap.get(Integer.valueOf(key));
            if (Constants.INTEGER_31.equals(Integer.valueOf(key))) {
                if (Objects.nonNull(data)) {
                    float v = data.getValue().floatValue();
                    if (v <= lonLatRangeTemp.getEndLon() || v >= lonLatRangeTemp.getStartLon()) {
                        lonLatList.add(v);
                    } else {
                        lonLatList = null;
                    }
                } else {
                    lonLatList = null;
                }
            } else if (Constants.INTEGER_32.equals(Integer.valueOf(key))) {
                if (Objects.nonNull(data)) {
                    float v = data.getValue().floatValue();
                    if (v <= lonLatRangeTemp.getEndLat() || v >= lonLatRangeTemp.getStartLat()) {
                        lonLatList.add(v);
                    } else {
                        lonLatList = null;
                    }
                } else {
                    lonLatList = null;
                }
            } else if (Constants.INTEGER_211.equals(Integer.valueOf(key))) {
                if (Objects.nonNull(data)) {
                    float v = data.getValue().floatValue();
                    lonLatList.add(v);
                } else {
                    lonLatList.add(Constants.FLOAT_0);
                }
            } else {
                if (Objects.nonNull(data)) {
                    pvbList.add(Optional.ofNullable(data.getValue()).orElse(new BigDecimal(-999)).floatValue());
                } else {
                    pvbList.add(Constants.FLOAT_N_999);
                }
                if (Objects.nonNull(Ugm3Data)) {
                    ugm3List.add(Optional.ofNullable(Ugm3Data.getValue()).orElse(new BigDecimal(-999)).floatValue());
                } else {
                    ugm3List.add(Constants.FLOAT_N_999);
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
        stationCodeS.stream().distinct().collect(Collectors.toList());
        SailParamTemp sailParamTemp = SailParamTemp.builder()
                .startTime(vocHistoryParam.getStartTime())
                .endTime(vocHistoryParam.getEndTime())
                .stationCodeS(stationCodeS)
                .build();
        List<VocHistoryOut> vocHistoryOutList = vocRepository.queryHistoryList(sailParamTemp);
        if (CollectionUtils.isEmpty(vocHistoryOutList)) {
            return Collections.emptyList();
        }

        //去除正在运行的任务,及任务结束时间为空的就为正在运行的任务
        vocHistoryOutList = vocHistoryOutList.stream().filter(e -> e.getTimeE() != null).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(vocHistoryOutList)) {
            return Collections.emptyList();
        }

        //获取任务对应的因子配置信息
        List<Long> sailIds = vocHistoryOutList.stream().map(VocHistoryOut::getTaskId).distinct().collect(Collectors.toList());
        //查询当前任务下配置了哪些因子
        Map<Long, List<Integer>> parametersMap = queryParametersBySailIds(sailIds);


        List<String> startUserIds = vocHistoryOutList.stream().map(VocHistoryOut::getStartUserId).collect(Collectors.toList());
        List<String> endUserIds = vocHistoryOutList.stream().map(VocHistoryOut::getEndUserId).collect(Collectors.toList());
        startUserIds.addAll(endUserIds);
        startUserIds = startUserIds.stream().distinct().collect(Collectors.toList());

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
            List<Integer> parameters = parametersMap.get(vocHistoryOut.getTaskId());
            vocHistoryOut.setItemAry(parameters);
        }
        return vocHistoryOutList;
    }

    /**
     * 查询当前任务下配置了哪些因子
     *
     * @param sailIds
     * @return java.util.List<java.lang.Integer>
     * @author 3Clear1
     * @date 2020/8/22 16:35
     **/
    private Map<Long, List<Integer>> queryParametersBySailIds(List<Long> sailIds) {
        List<SailParameter> sailParameters = vocRepository.queryParametersBySailIds(sailIds);
        if (CollectionUtils.isNotEmpty(sailParameters)) {
            //将sail_parameter表中以逗号分隔的因子转换成因子数组
            Map<Long, List<Integer>> sailIdParameterMap = new HashMap<>();
            for (SailParameter e : sailParameters) {
                List<String> strings = Arrays.asList(Optional.ofNullable(e.getParameterid()).orElse(null).split(",")).stream().filter(item -> Objects.nonNull(item)).collect(Collectors.toList());
                List<Integer> integerList = Collections.EMPTY_LIST;
                if (CollectionUtils.isNotEmpty(strings)) {
                    integerList = strings.stream().map(item -> Integer.valueOf(item)).collect(Collectors.toList());
                }
                sailIdParameterMap.put(e.getSailId(), integerList);
            }
            return sailIdParameterMap;
        }
        return Maps.newLinkedHashMap();
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

        //设置taskid下的因子配置
        Long taskID = latestBusInfoOut.getTaskID();
        Map<Long, List<Integer>> integerListMap = queryParametersBySailIds(Arrays.asList(taskID));
        List<Integer> parameters = integerListMap.get(taskID);
        latestBusInfoOut.setItemAry(parameters);

        latestBusInfoOut.setSailing(sailing);
        return latestBusInfoOut;
    }

    @Override
    public OneVocOutInfo queryOneVodData(OneVocParam oneVocParam) {

        String stationCode = oneVocParam.getStationcode();
        //根据走航车编码查询voc的仪器编码，查询的是instrument表
        Instrument vocInstrument = Instrument.builder()
                .stationcode(stationCode)
                .model(Constants.VOC)
                .build();
        List<Instrument> vocInstrumentOutPuts = getInstrument(vocInstrument);
        if (CollectionUtils.isEmpty(vocInstrumentOutPuts)) {
            throw new ClearArgumentException("导航车查询的Voc设备为空");
        }
        //因为传进来的设备比如说是voc，他只会查询voc的数据，但是我们必须要用到gps数据，
        //所以还需要根据这辆车查询到对应的gps的设备id
        //根据走航车编码查询GPS的仪器编码，查询的是instrument表
        Instrument gpsInstrument = Instrument.builder()
                .stationcode(stationCode)
                .model(Constants.GPS)
                .build();
        List<Instrument> gpsInstrumentOutPuts = getInstrument(gpsInstrument);
        if (CollectionUtils.isEmpty(gpsInstrumentOutPuts)) {
            throw new ClearArgumentException("导航车查询的GPS设备为空");
        }

        //查询data表数据
        OneVocParamTemp oneVocParamTemp = sailInfoConverter.oneVocParamConvert(oneVocParam);
        //入参除了传进来的参数，还需要加上经度，纬度和高层
        oneVocParamTemp.setParameterids(Arrays.asList(oneVocParam.getParameterid(), Constants.INTEGER_31, Constants.INTEGER_32, Constants.INTEGER_211));
        //设备id除了传进来的id，还要加上gps的id
        List<Integer> instrumentids = Arrays.asList(vocInstrument.getInstrumentid(), gpsInstrumentOutPuts.get(0).getInstrumentid());
        oneVocParamTemp.setInstrumentids(instrumentids);
        List<Data> dataList = vocRepository.queryOneVodData(oneVocParamTemp);

        //当返回结果为空直接返回
        if (CollectionUtils.isEmpty(dataList)) {
            return new OneVocOutInfo();
        }
        //查询到的数据根据时间分组
        Map<Long, List<Data>> dateTimeListMap = dataList.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));

        //根据走航车编码查询voc的的频率
        Integer durationid = vocInstrumentOutPuts.get(0).getDurationid();

        //查询TvOC等四个衍生数据
        VocDerviedTemp vocDerviedTemp = VocDerviedTemp.builder()
                .durationid(durationid)
                .instrumentid(vocInstrument.getInstrumentid())
                .startTime(oneVocParam.getStartTime())
                .endTime(oneVocParam.getEndTime())
                .build();
        List<DataDerived> dataDeriveds = getDerivedsVocData(vocDerviedTemp);
        Map<Long, List<DataDerived>> dataDerivedMap = dataDeriveds.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));


        //查询data_voc表，计算因子mg/立方米数据
        VocTemp vocTemp = sailInfoConverter.oneUgm3Convert(oneVocParam);
        vocTemp.setDurationid(durationid);
        vocTemp.setInstrumentids(Arrays.asList(vocInstrument.getInstrumentid()));
        vocTemp.setParameterids(Arrays.asList(oneVocParam.getParameterid()));
        List<DataVoc> ugm3Data = getUgm3Data(vocTemp);
        Map<Long, List<DataVoc>> ugm3Map = ugm3Data.stream().collect(Collectors.groupingBy(e -> Long.valueOf(dateTimeFormatter.format(e.getLst()))));

        //将查询到的数据封装成需要返回的结果样式
        LinkedHashMap<Integer, String> parameterMap = new LinkedHashMap<>();
        LinkedList<Integer> parameters = new LinkedList<>();
        //经度
        parameters.add(Constants.INTEGER_31);
        //纬度
        parameters.add(Constants.INTEGER_32);
        //高度
        parameters.add(Constants.INTEGER_211);
        //传入的因子
        parameters.add(oneVocParam.getParameterid());


        //经纬度范围
        LonLatRangeTemp lonLatRangeTemp = sailInfoConverter.lonLatRangeConvert(oneVocParam);


        VocHisTimeInfoOut vocInfoOut = getVocHisTimeInfoOut(parameters, null, dataDerivedMap, ugm3Map, dateTimeListMap, lonLatRangeTemp);

        List<Long> timeAry = vocInfoOut.getTimeAry();
        List<List<Float>> ptAry = vocInfoOut.getPtAry();
        List<List<Float>> dataAryPVB = vocInfoOut.getDataAryPVB();
        List<List<Float>> dataAryUgm3 = vocInfoOut.getDataAryUgm3();
        List<List<Float>> dataAryZhenDuan = vocInfoOut.getDataAryZhenDuan();
        LinkedList<Float> PVB = new LinkedList<>();
        LinkedList<Float> Ugm3 = new LinkedList<>();
        for (List<Float> floats : dataAryPVB) {
            PVB.add(floats.get(0));
        }
        for (List<Float> floats : dataAryUgm3) {
            Ugm3.add(floats.get(0));
        }

        OneVocOutInfo oneVocInfoOut = OneVocOutInfo.builder()
                .timeAry(timeAry)
                .ptAry(ptAry)
                .dataAryPVB(PVB)
                .dataAryUgm3(Ugm3)
                .dataAryZhenDuan(dataAryZhenDuan)
                .build();

        return oneVocInfoOut;
    }

    @Override
    public Boolean generateConfigurationInfo() {
        //查询所有的历史任务
        List<Sail> sailList = vocRepository.querySailAll();
        if (CollectionUtils.isEmpty(sailList)) {
            return false;
        }
        //遍历所有的任务，查询每个任务有哪些因子，回写到任务因子配置表(sail_parameter)中
        for (Sail sail : sailList) {
            //查询当前任务有哪些因子
            List<Integer> parameters = vocRepository.queryParametersByParam(sail);
            //移除经纬度和高度的因子id
            parameters.removeAll(Arrays.asList(Constants.INTEGER_31, Constants.INTEGER_32, Constants.INTEGER_211));
            //将因子以逗号分隔转为字符串
            String parameterStr = StringUtils.join(parameters.toArray(), ",");

            SailParameter sailParameter = SailParameter.builder()
                    .sailId(sail.getSailId())
                    .parameterid(parameterStr)
                    .build();
            int count = vocRepository.insertSailParameter(sailParameter);
        }
        return true;
    }

    @Override
    public List<ItemInfoOut> generatorItem() {
        //查询设置为8的所有因子
        List<Integer> parameters = vocRepository.selectInstrumentparameters(8);

        if (CollectionUtils.isEmpty(parameters)) {
            return Collections.emptyList();
        }

        //查询因子的所有名称
        List<ParameterInfoOut> parameterInfos = vocRepository.selectParameters(parameters);
        if (CollectionUtils.isEmpty(parameterInfos)) {
            return Collections.emptyList();
        }

        LinkedList<ItemInfoOut> result = new LinkedList<>();
        for (ParameterInfoOut parameterInfo : parameterInfos) {
            ItemInfoOut infoOut = ItemInfoOut.builder()
                    .id(parameterInfo.getParameterid())
                    .name(parameterInfo.getName())
                    .show(1)
                    .min(0)
                    .max(50)
                    .maxug(1000)
                    .td(30)
                    .tdug(500)
                    .scale(1000)
                    .scaleug(10)
                    .index(0)
                    .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                    .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                    .industry(Arrays.asList("行业1", "行业2"))
                    .build();
            result.add(infoOut);
        }
//        衍生数据类型：0-最大voc、1-TVOC、2-最大OFP、3-最大SOAP、4-OFP、5-SOAP、6-OFP总和、7-SOAP总和
        ItemInfoOut zdvoc = ItemInfoOut.builder()
                .id(0)
                .name("最大voc")
                .show(1)
                .min(0)
                .max(50)
                .maxug(1000)
                .td(30)
                .tdug(500)
                .scale(1000)
                .scaleug(10)
                .index(0)
                .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                .industry(Arrays.asList("行业1", "行业2"))
                .build();
        ItemInfoOut TVOC = ItemInfoOut.builder()
                .id(1)
                .name("TVOC")
                .show(1)
                .min(0)
                .max(50)
                .maxug(1000)
                .td(30)
                .tdug(500)
                .scale(1000)
                .scaleug(10)
                .index(0)
                .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                .industry(Arrays.asList("行业1", "行业2"))
                .build();
        ItemInfoOut zdofp = ItemInfoOut.builder()
                .id(2)
                .name("最大OFP")
                .show(1)
                .min(0)
                .max(50)
                .maxug(1000)
                .td(30)
                .tdug(500)
                .scale(1000)
                .scaleug(10)
                .index(0)
                .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                .industry(Arrays.asList("行业1", "行业2"))
                .build();
        ItemInfoOut OFP = ItemInfoOut.builder()
                .id(4)
                .name("OFP")
                .show(1)
                .min(0)
                .max(50)
                .maxug(1000)
                .td(30)
                .tdug(500)
                .scale(1000)
                .scaleug(10)
                .index(0)
                .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                .industry(Arrays.asList("行业1", "行业2"))
                .build();
        ItemInfoOut SOAP = ItemInfoOut.builder()
                .id(5)
                .name("SOAP")
                .show(1)
                .min(0)
                .max(50)
                .maxug(1000)
                .td(30)
                .tdug(500)
                .scale(1000)
                .scaleug(10)
                .index(0)
                .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                .industry(Arrays.asList("行业1", "行业2"))
                .build();
        ItemInfoOut ofpzh = ItemInfoOut.builder()
                .id(6)
                .name("OFP总和")
                .show(1)
                .min(0)
                .max(50)
                .maxug(1000)
                .td(30)
                .tdug(500)
                .scale(1000)
                .scaleug(10)
                .index(0)
                .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                .industry(Arrays.asList("行业1", "行业2"))
                .build();
        ItemInfoOut soapzh = ItemInfoOut.builder()
                .id(7)
                .name("SOAP总和")
                .show(1)
                .min(0)
                .max(50)
                .maxug(1000)
                .td(30)
                .tdug(500)
                .scale(1000)
                .scaleug(10)
                .index(0)
                .values(Arrays.asList(0, 20, 40, 60, 80, 100))
                .valuesug(Arrays.asList(0, 200, 400, 600, 800, 1000))
                .industry(Arrays.asList("行业1", "行业2"))
                .build();
        result.add(zdvoc);
        result.add(TVOC);
        result.add(zdofp);
        result.add(OFP);
        result.add(SOAP);
        result.add(ofpzh);
        result.add(soapzh);

        return result;
    }

    @Override
    public void generatorWind(WindInfo windInfo) {
        Head header = windInfo.getHeader();
        Integer nx = header.getNx();
        Integer ny = header.getNy();
        //定义一个二维数组
        Float[][] uGrid = new Float[nx][ny];
        Float[][] vGrid = new Float[nx][ny];
        com.clear.param.input.Data data = windInfo.getData();


        List<Float> uComp = data.getOne();
        List<Float> vComp = data.getTwo();

        for (int i = 0; i < ny; i++) {
            for (int j = 0; j < nx; j++) {
                uGrid[i][j] = uComp.get(i * nx + j);
                vGrid[i][j]  = vComp.get(i * nx + j);
            }
        }
        System.out.println(uGrid.toString());
        System.out.println(vGrid.toString());
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
