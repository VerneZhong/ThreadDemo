package com.zhongxb.concurrent.chapter05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 自定义显式锁
 * @author zxb
 */
public interface Lock {

    /**
     * 获取锁，该方法永远被阻塞，除非获得了锁，可被中断
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 获取锁，超时时间
     * @param timeout
     * @throws InterruptedException
     * @throws TimeoutException
     * @throws IllegalArgumentException
     */
    void lock(long timeout) throws InterruptedException, TimeoutException, IllegalArgumentException;

    /**
     * 释放锁
     */
    void unlock();

    /**
     * 获取阻塞线程列表
     * @return
     */
    List<Thread> getBlockedThreads();

    /**
     * 获取阻塞线程数量
     * @return
     */
    int getBlockedThreadCount();
}
