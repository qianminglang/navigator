package com.clear.util;

import java.io.File;
import java.nio.file.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName FileWatchUtil
 *
 * @author qml
 * Date 2020/8/11 10:25
 * Version 1.0
 **/

public class FileWatchUtil {

    private static String path = "D:\\clear\\zhc\\input";

    public static void getFile(){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                WatchKey key;
                try {
                    WatchService watchService = FileSystems.getDefault().newWatchService();
                    Paths.get(path).register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                    while (true) {
                        File file = new File(path);//path为监听文件夹
                        File[] files = file.listFiles();
                        key = watchService.take();//没有文件增加时，阻塞在这里
                        for (WatchEvent<?> event : key.pollEvents()) {
                            String fileName = path + "\\" + event.context();
                        }
                        if (!key.reset()) {
                            break; //中断循环
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //第一个数字2000表示，2000ms以后开启定时器,第二个数字3000，表示3000ms后运行一次run
        }, 2000, 3000);
    }
}