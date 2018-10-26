package com.zhongxb.concurrent.chapter08;

import java.util.concurrent.TimeUnit;

/**
 * 线程池build类
 * @author zxb
 */
public class ThreadPoolBuilder {
    /**
     * 初始化线程的数量
     */
    private int initSize;

    /**
     * 线程池最大线程数量
     */
    private int maxSize;

    /**
     * 线程池核心线程的数量
     */
    private int coreSize;

    /**
     * 任务队列到达上限了的默认拒绝策略
     */
    public DenyPolicy denyPolicy;

    private long keepAliveTime;

    private TimeUnit timeUnit;

    /**
     * 任务队列最大任务数
     */
    private int queueMaxSize;

    public ThreadPoolBuilder setInitSize(int initSize) {
        this.initSize = initSize;
        return this;
    }

    public ThreadPoolBuilder setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public ThreadPoolBuilder setCoreSize(int coreSize) {
        this.coreSize = coreSize;
        return this;
    }

    public ThreadPoolBuilder setDenyPolicy(DenyPolicy denyPolicy) {
        this.denyPolicy = denyPolicy;
        return this;
    }

    public ThreadPoolBuilder setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ThreadPoolBuilder setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public ThreadPoolBuilder setQueueMaxSize(int queueMaxSize) {
        this.queueMaxSize = queueMaxSize;
        return this;
    }

    public int getInitSize() {
        return initSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getCoreSize() {
        return coreSize;
    }

    public DenyPolicy getDenyPolicy() {
        return denyPolicy;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public int getQueueMaxSize() {
        return queueMaxSize;
    }

    public BasicThreadPool build() {
        return new BasicThreadPool(this);
    }
}
