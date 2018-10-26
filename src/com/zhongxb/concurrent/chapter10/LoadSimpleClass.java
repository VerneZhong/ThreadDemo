package com.zhongxb.concurrent.chapter10;

public class LoadSimpleClass {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader();

        Class<?> aClass = classLoader.loadClass("com.zhongxb.concurrent.chapter10.SimpleClass");
        System.out.println(classLoader.getParent());
        aClass.newInstance();
    }
}
