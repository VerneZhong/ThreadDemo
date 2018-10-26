package com.zhongxb.concurrent.chapter08;

/**
 * 线程工厂
 * @author zxb
 */
@FunctionalInterface
public interface ThreadFactory {

    /**
     * 创建线程
     * @param runnable
     * @return
     */
    Thread createThread(Runnable runnable);
}
