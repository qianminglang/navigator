package com.clear.navigator.util;

import com.clear.navigator.dto.GpsInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName ReadGps
 *
 * @author qml
 * Date 2020/8/6 16:34
 * Version 1.0
 **/
@Slf4j
public class ReadGps {
    public static List<GpsInfo> readTxtData(String fileName) {
        // 使用循环读取数据
        ArrayList<GpsInfo> gpsInfos = new ArrayList<>();
        try {
            // 打开文件输入流
            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            log.info("in,{}",in.toString());

            //几行器，每两行一次循环
            String line = "";

            while ((line = in.readLine()) != null) {
                String[] lineOne = line.split("\\s{1,}|\\t");
                String[] lineTwo = in.readLine().split("\\s{1,}|\\t");
                GpsInfo build = GpsInfo.builder()
                        .time(lineOne[0] + lineOne[1])
                        .lon(lineOne[3])
                        .lat(lineTwo[3])
                        .build();
                gpsInfos.add(build);
            }
        } catch (IOException e) {
            log.info("读取文件失败:{}", e);
        }
        return gpsInfos;
    }
}