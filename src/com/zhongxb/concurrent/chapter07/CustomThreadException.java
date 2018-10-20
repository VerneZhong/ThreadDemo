package com.zhongxb.concurrent.chapter07;

/**
 * 自定义线程异常
 * @author zxb
 */
public class CustomThreadException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName() + "occur exception.");
        e.printStackTrace();
    }
}
