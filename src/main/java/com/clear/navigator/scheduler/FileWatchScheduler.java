package com.clear.navigator.scheduler;

import com.clear.navigator.config.FileListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * ClassName FileWatchScheduler
 *
 * @author qml
 * Date 2020/8/11 13:06
 * Version 1.0
 **/
@Component
@Slf4j
public class FileWatchScheduler {

    @Autowired
    private FileListener fileListener;

    public static FileWatchScheduler fileWatchScheduler;

    @PostConstruct
    public void init() {
        fileWatchScheduler = this;
        fileWatchScheduler.fileListener = this.fileListener;
        fileWatchScheduler.startFileWatch();
    }

    public FileWatchScheduler() {
        log.info("FileWatchScheduler初始化");
    }

    public void startFileWatch() {
        // 监控目录
        String rootDir = "D:\\clear\\zhc\\input";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".txt"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        // 使用过滤器
//        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
        //不使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(fileListener);
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}