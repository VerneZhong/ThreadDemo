package com.zhongxb.concurrent.chapter14;

/**
 * 枚举内部类,单例模式
 */
public class Singleton7 {

    private byte[] bytes = new byte[1024];

    private Singleton7() {}

    private enum EnumHolder {

        INSTANCE;

        private Singleton7 singleton7;

        EnumHolder() {
            this.singleton7 = new Singleton7();
        }

        public Singleton7 getSingleton7() {
            return singleton7;
        }
    }

    public static Singleton7 getInstance() {
        return EnumHolder.INSTANCE.getSingleton7();
    }
 }
