package com.zhongxb.concurrent.chapter14;

/**
 * 饿汉式的单例设计
 */
public final class Singleton {

    private byte[] bytes = new byte[1024];

    private static Singleton singleton = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return singleton;
    }
}
