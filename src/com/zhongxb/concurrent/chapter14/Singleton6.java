package com.zhongxb.concurrent.chapter14;

/**
 * 枚举方式，单例模式
 */
public enum Singleton6 {

    INSTANCE;

    private byte[] bytes = new byte[1024];

    Singleton6() {
        System.out.println("INSTANCE will be initialized immediately.");
    }

    public static void method() {

    }

    public static Singleton6 getInstance() {
        return INSTANCE;
    }
}
