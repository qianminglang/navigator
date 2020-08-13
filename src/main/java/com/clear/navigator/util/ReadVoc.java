package com.clear.navigator.util;

import com.clear.navigator.dto.VocInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName ReadGps
 *
 * @author qml
 * Date 2020/8/6 16:34
 * Version 1.0
 **/

public class ReadVoc {

    public static void main(String[] args) {
        File file = new File("D:\\hongda\\voc.txt");
        ReadVoc.txt2String(file);
    }

    /**
     * 读取voc文件并返回集合对象
     */
    public static List<VocInfo> txt2String(File file) {
        List<VocInfo> arrayList = new ArrayList<>();
        try {
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            //使用readLine方法，一次读一行

            while ((s = br.readLine()) != null) {
                String[] line = s.split("\\s{1,}|\\t");
                VocInfo build = VocInfo.builder()
                        .time(line[0] + line[1])
                        .name(line[2])
                        .value(line[3])
                        .build();
                arrayList.add(build);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * 输出voc字典文件
     */
    public static Map<String,String> writeFile(String dictPath, List<VocInfo> arrayList) {
        Map<String, String> map = new LinkedHashMap<>();
        FileOutputStream fileOutputStream = null;
        File file = new File(dictPath);
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            //根据skuAttrValueName值去重
            List<VocInfo> uniqueSkuValues = arrayList.stream().collect(
                    Collectors.collectingAndThen(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(o -> o.getName()))), ArrayList::new)
            );
            int i = 0;
            for (VocInfo uniqueSkuValue : uniqueSkuValues) {
                String oneLine = uniqueSkuValue.getName()+"  "+i;
                fileOutputStream.write(oneLine.getBytes());
                String huanhang = System.getProperty("line.separator");
                fileOutputStream.write(huanhang.getBytes());
                map.put(uniqueSkuValue.getName(),i+"");
                i++;
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}