package com.zhongxb.concurrent.chapter10;

public class LoadString {

    public static void main(String[] args) throws ClassNotFoundException {

        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader();

        Class<?> aClass = classLoader.loadClass("java.lang.String");
    }
}
