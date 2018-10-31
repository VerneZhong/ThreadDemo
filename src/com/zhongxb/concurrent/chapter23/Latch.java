package com.zhongxb.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * 无限等待的latch
 * @author Mr.zxb
 * @date 2018-10-31 13:57
 */
public abstract class Latch {

    /**
     * 用于控制多少个线程完成时才能打开阀门
     */
    protected int limit;

    /**
     * 增加回调功能
     */
    protected Runnable runnable;

    /**
     * 通过构造传入limit
     * @param limit
     */
    public Latch(int limit) {
        this.limit = limit;
    }

    public Latch(int limit, Runnable runnable) {
        this.limit = limit;
        this.runnable = runnable;
    }

    /**
     * 该方法会使得当前线程一直等待，直到所有的线程都完成工作，被阻塞的线程是允许被中断的
     * @throws InterruptedException
     */
    public abstract void await() throws InterruptedException;

    /**
     * 可超时的等待
     * @param time
     * @param timeUnit
     * @throws InterruptedException
     * @throws WaitTimeoutException
     */
    public abstract void await(long time, TimeUnit timeUnit) throws InterruptedException, WaitTimeoutException;

    /**
     * 当任务线程完成工作之后调用该方法使得计算器减1
     */
    public abstract void countDown();

    /**
     * 获取当前还有多少个线程没有完成任务
     * @return
     */
    public abstract int getUnarrived();
}
