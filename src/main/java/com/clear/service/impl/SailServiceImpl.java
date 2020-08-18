package com.clear.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clear.consts.Constants;
import com.clear.converter.input.SailInfoConverter;
import com.clear.domain.VocTemp;
import com.clear.entity.Data;
import com.clear.entity.Instrument;
import com.clear.entity.Sail;
import com.clear.exception.ClearArgumentException;
import com.clear.mapper.SailMapper;
import com.clear.param.input.UserIdParam;
import com.clear.param.input.VocParam;
import com.clear.param.output.SiteOut;
import com.clear.param.output.VocInfoOut;
import com.clear.repository.VocRepository;
import com.clear.service.SailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    @Resource
    private SailInfoConverter sailInfoConverter;
    @Resource
    private VocRepository vocRepository;

    @Override
    public List<SiteOut> queryUserSite(UserIdParam userIdParam) {
        List<SiteOut> siteOuts = vocRepository.queryUserSite(userIdParam);
        return siteOuts;
    }

    @Override
    public VocInfoOut queryVocData(VocParam vocParam) {
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
        List<Integer> parameters = vocRepository.queryInstrumentParameter(instrumentid);


        Integer durationid = instrumentS.get(0).getDurationid();
        ArrayList<Integer> instrumentids = new ArrayList<>();

        //查询因子数据既要查询voc，也要查询gps（经纬度）
        instrumentids.add(instrumentid);
        instrumentids.add(Constants.INTEGER_5);
        vocTemp.setInstrumentids(instrumentids);
        vocTemp.setDurationid(durationid);
        List<Data> dataList = vocRepository.queryVocData(vocTemp);

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
            for (Data data : groupData) {
                //31表示经度
                if (Constants.INTEGER_31.equals(data.getParameterid())) {
                    lonLatList.add(data.getValue().floatValue());
                }
                //32表示纬度
                if (Constants.INTEGER_32.equals(data.getParameterid())) {
                    lonLatList.add(data.getValue().floatValue());
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
            lonLatList.add(0f);
            ptAry.add(lonLatList);
            dataAry.add(parameterValueList);
        }
        VocInfoOut vocInfoOut = VocInfoOut.builder()
                .timeAry(timeAry)
                .ptAry(ptAry)
                .dataAry(dataAry)
                .build();
        return vocInfoOut;
    }
}
