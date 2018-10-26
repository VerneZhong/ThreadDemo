package com.zhongxb.concurrent.chapter11;

import com.zhongxb.concurrent.chapter10.BrokerDelegateClassLoader;

/**
 * 线程上下文类加载器
 */
public class MainThreadClassLoader {

    public static void main(String[] args) {

        Thread.currentThread().setContextClassLoader(new BrokerDelegateClassLoader());

        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
