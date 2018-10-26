package com.zhongxb.concurrent.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试加载自定义类加载器
 * 绕过ApplicationClassLoader的方式，去使用自定义类加载器
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // 默认父加载器加载
//        MyClassLoader classLoader = new MyClassLoader();

        // 1.绕过系统类加载器，直接将扩展类加载器作为自定义类加载器的父加载器
//        ClassLoader extClassLoader = MyClassLoaderTest.class.getClassLoader().getParent();
        // 声明一个ClassLoader
//        MyClassLoader classLoader = new MyClassLoader("d:\\classloader1", extClassLoader);

        // 2.在构造MyClassLoader的时候指定一个null的父加载器
//        MyClassLoader classLoader = new MyClassLoader("d:\\classloader1", null);

        // 使用myClassLoader加载hello world class
//        Class<?> aClass = classLoader.loadClass("com.zhongxb.concurrent.chapter10.HelloWorld");

        // 打破双亲委派机制的自定义类加载器
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader();
        Class<?> aClass = classLoader.loadClass("com.zhongxb.concurrent.chapter10.HelloWorld");

        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

        Object hello = aClass.newInstance();
        System.out.println(hello);
        Method welcome = aClass.getMethod("welcome");
        String result = (String) welcome.invoke(hello);
        System.out.println("result:" + result);
    }
}
