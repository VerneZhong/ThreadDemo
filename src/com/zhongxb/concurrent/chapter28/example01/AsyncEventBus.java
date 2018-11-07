package com.zhongxb.concurrent.chapter28.example01;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步的方式，推送Event
 * @author Mr.zxb
 * @date 2018-11-06 16:37
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(String busName, EventExceptionHandler eventExceptionHandler, ThreadPoolExecutor executor) {
        super(busName, eventExceptionHandler, executor);
    }

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        super(busName, null, executor);
    }

    public AsyncEventBus(EventExceptionHandler eventExceptionHandler, ThreadPoolExecutor executor) {
        this("default-async", eventExceptionHandler, executor);
    }
}
