package com.clear.navigator.util;
import java.util.Comparator;
import java.util.Map;

/**
 * 字符串排序
 *
 * @param
 * @author 3Clear1
 * @date 2020/8/7 10:16
 * @return
 **/
public class StringComparator implements Comparator<Map.Entry> {
    @Override
    public int compare(Map.Entry entryOne, Map.Entry entryTwo) {
        Object keyOne = entryOne.getKey();
        Object keyTwo = entryTwo.getKey();
        return keyOne.toString().compareTo(keyTwo.toString());
    }
}