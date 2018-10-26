package com.zhongxb.concurrent.chapter10;

/**
 * 相同类加载器，不同实例加载同一个class，也会产生多个class对象
 */
public class NameSpace3 {

    public static void main(String[] args) throws ClassNotFoundException {

        BrokerDelegateClassLoader classLoader1 = new BrokerDelegateClassLoader();
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
