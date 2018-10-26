package com.zhongxb.concurrent.chapter14;

/**
 * 懒汉式+同步方式
 */
public final class Singleton3 {

    private static byte[] bytes = new byte[1024];

    private static Singleton3 singleton = null;

    private Singleton3() {}

    public synchronized static Singleton3 getInstance() {
        if (singleton == null) {
            singleton = new Singleton3();
        }
        return singleton;
    }
}
