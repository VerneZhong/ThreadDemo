package com.zhongxb.concurrent.chapter14;

/**
 * Holder 方式
 */
public final class Singleton5 {

    private static byte[] bytes = new byte[1024];

    private Singleton5() {
    }

    private static class Holder {
        private static Singleton5 singleton = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Holder.singleton;
    }
}
