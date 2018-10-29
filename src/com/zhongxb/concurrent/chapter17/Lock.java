package com.zhongxb.concurrent.chapter17;

/**
 * 锁接口
 * @author Mr.zxb
 * @date 2018-10-29 13:39
 */
public interface Lock {

    /**
     * 获取显示锁，没有获得锁的线程将被阻塞
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 释放获取的锁
     */
    void unlock();

}
