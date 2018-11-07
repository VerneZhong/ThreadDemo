package com.zhongxb.concurrent.chapter28.example02;

import com.zhongxb.concurrent.chapter28.example01.AsyncEventBus;
import com.zhongxb.concurrent.chapter28.example01.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mr.zxb
 * @date 2018-11-07 15:04
 */
public class MonitorTest {

    public static void main(String[] args) throws Exception {
        // 创建线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        EventBus eventBus = new AsyncEventBus("testBus", executor);

        // 注册
        eventBus.register(new FileChangeListener());

        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "D:\\monitor");

        monitor.startMonitor();

    }
}
