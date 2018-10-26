package com.zhongxb.concurrent.chapter10;

/**
 * 不同类加载器加载同一个class
 * 使用不同的类加载器，或者同一个类加载器的不同实例，去加载同一个class，
 * 则会在堆内存和方法区产生多个class对象
 */
public class NameSpace2 {

    public static void main(String[] args) throws ClassNotFoundException {

        MyClassLoader classLoader1 = new MyClassLoader("d:\\classloader1", null);
        BrokerDelegateClassLoader classLoader2 = new BrokerDelegateClassLoader();

        Class<?> aClass = classLoader1.loadClass("com.zhongxb.concurrent.chapter10.HelloWorld");
        Class<?> bClass = classLoader2.loadClass("com.zhongxb.concurrent.chapter10.HelloWorld");

        System.out.println("aClassLoader: " + aClass.getClassLoader());
        System.out.println("bClassLoader: " + bClass.getClassLoader());
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);
    }
}
