package com.zhongxb.concurrent.chapter14;

/**
 * volatile + double-check 懒汉式，双重校验
 */
public final class Singleton4 {

    private static byte[] bytes = new byte[1024];

    private static volatile Singleton4 singleton = null;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (singleton == null) {
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
