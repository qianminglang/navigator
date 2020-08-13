package com.clear.navigator.service;

import com.alibaba.fastjson.JSON;
import com.clear.navigator.dto.GpsInfo;
import com.clear.navigator.dto.JsonLastResult;
import com.clear.navigator.dto.VocInfo;
import com.clear.navigator.util.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName FileToJson
 *
 * @author qml
 * Date 2020/8/6 18:24
 * Version 1.0
 **/

public class FileToLastJson {

    public static Object fileTojson() {
        String DICT_PATH = "D:\\hongda\\voc_dict.txt";
        String filePath = "D:\\hongda\\gps.txt";
        List<GpsInfo> gpsInfos = ReadGps.readTxtData(filePath);

        File file = new File("D:\\hongda\\voc.txt");
        File jsonFile = new File("D:\\hongda\\result.json");
        List<VocInfo> vocInfos = ReadVoc.txt2String(file);
        Map<String, String> staticParameters = ReadVoc.writeFile(DICT_PATH, vocInfos);
        Map<String, GpsInfo> gpsInfoMap = gpsInfos.stream().collect(Collectors.toMap(GpsInfo::getTime, e -> e, (oldValue, newValue) -> newValue));

        //vocInfos按时间分组
        Map<String, List<VocInfo>> VocInfoGroupByTime = vocInfos.stream().collect(Collectors.groupingBy(VocInfo::getTime));
        List<Map.Entry<String, List<VocInfo>>> entries = new ArrayList<>(VocInfoGroupByTime.entrySet());
        //按时间排序
        Collections.sort(entries, new StringComparator());
        Map<String, String> initParam = null;

        LinkedList<Long> timeList = new LinkedList<>();
        LinkedList<List<Float>> lonLatList = new LinkedList<>();
        LinkedList<List<Float>> valueList = new LinkedList<>();
        for (Map.Entry<String, List<VocInfo>> stringListEntry : entries) {
            //设置初始值
            initParam = (Map<String, String>) Clone.deepClone(staticParameters);
            for (Map.Entry<String, String> stringIntegerEntry : initParam.entrySet()) {
                stringIntegerEntry.setValue("-999");
            }
            String time = stringListEntry.getKey();
            List<VocInfo> value = stringListEntry.getValue();
            for (VocInfo vocInfo : value) {
                initParam.put(vocInfo.getName(), vocInfo.getValue());
            }

            LinkedList<Float> lonLat = new LinkedList<>();
            lonLat.add((Float.valueOf(gpsInfoMap.get(time).getLon())));
            lonLat.add((Float.valueOf(gpsInfoMap.get(time).getLat())));
            Date parse = DateUtil.stringToDate(time, DateUtil.YYYY_M_DD_HH_MM_SS);
            String timeFormat = DateUtil.dateToString(parse, DateUtil.YYYY_MM_DD_HH_MM_SS);
            timeFormat = timeFormat.replace("/", "").replace(":", "").replaceAll(" ", "");
            Long timeLong = Long.valueOf(timeFormat);
            timeList.add(timeLong);
            lonLatList.add(lonLat);
            LinkedList<String> values = new LinkedList<>(initParam.values());
            List<Float> floats = values.stream().map(Float::valueOf).collect(Collectors.toList());
            valueList.add(floats);
        }
        JsonLastResult build = JsonLastResult.builder()
                .time(timeList)
                .lonLat(lonLatList)
                .value(valueList)
                .build();

        Object jsonResult = JSON.toJSON(build);
        FileUtil.writeJsonFile(jsonFile, jsonResult);
        return jsonResult;
    }
}

