package com.zhongxb.concurrent.chapter10;

/**
 * 扩展类加载器，它的父加载器是根加载器，扩展类加载器是由纯Java语言实现
 *
 */
public class ExtClassLoader {

    public static void main(String[] args) {

        System.out.println(System.getProperty("java.ext.dirs"));
    }
}
