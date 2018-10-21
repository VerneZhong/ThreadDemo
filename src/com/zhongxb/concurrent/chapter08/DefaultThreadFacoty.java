package com.zhongxb.concurrent.chapter08;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 默认线程工厂
 * @author zxb
 */
public class DefaultThreadFacoty implements ThreadFactory {

    private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

    private static final ThreadGroup group = new ThreadGroup("MyThreadPool" + GROUP_COUNTER.getAndDecrement());

    public static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
    }
}
