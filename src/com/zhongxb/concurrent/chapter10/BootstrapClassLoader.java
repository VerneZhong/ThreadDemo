package com.zhongxb.concurrent.chapter10;

/**
 * 根加载器，又称Bootstrap类加载器，是最顶层的加载器，其没有父加载器，是由C++编写，主要负责java核心类库的加载
 */
public class BootstrapClassLoader {

    public static void main(String[] args) {

        System.out.println("Bootstrap:" + String.class.getClassLoader());

        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
