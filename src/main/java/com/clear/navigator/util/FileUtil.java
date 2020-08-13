package com.clear.navigator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ClassName FileUtil
 *
 * @author qml
 * Date 2020/8/11 11:13
 * Version 1.0
 **/

public class FileUtil {

    /***
     * 写json文件
     */
    public static void writeJsonFile(File jsonFile, Object jsonResult) {
        try {
            OutputStream out = new FileOutputStream(jsonFile);
            byte[] b = jsonResult.toString().getBytes();
            for (int i = 0; i < b.length; i++) {
                out.write(b[i]);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 读文件
     */
}