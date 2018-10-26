package com.zhongxb.concurrent.chapter14;

/**
 * 懒汉式单例模式，非线程安全
 */
public final class Singleton2 {

    private static byte[] bytes = new byte[1024];

    private static Singleton2 singleton = null;

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
