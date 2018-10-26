package com.zhongxb.concurrent.chapter10;

/**
 * 类加载器命名空间
 * 同一个class对象，在同一个类加载器命名空间下是唯一的
 * @author zxb
 */
public class NameSpace {

    public static void main(String[] args) throws ClassNotFoundException {

        // 获取系统类加载器
        ClassLoader classLoader = NameSpace.class.getClassLoader();

        Class<?> clazza = classLoader.loadClass("com.zhongxb.concurrent.chapter10.HelloWorld");
        Class<?> clazzb = classLoader.loadClass("com.zhongxb.concurrent.chapter10.HelloWorld");

        System.out.println(clazza.hashCode());
        System.out.println(clazzb.hashCode());

        System.out.println(clazza == clazzb);
    }
}
