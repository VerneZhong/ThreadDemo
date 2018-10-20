package com.zhongxb.concurrent.chapter08;

/**
 * 线程工厂
 * @author zxb
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
