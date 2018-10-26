package com.zhongxb.concurrent.chapter08;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 默认线程工厂
 * @author zxb
 */
public class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

    private static final ThreadGroup GROUP = new ThreadGroup("MyThreadPoolGroup" + GROUP_COUNTER.getAndDecrement());

    public static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(GROUP, runnable, "thread-pool-" + COUNTER.getAndDecrement());
    }
}
