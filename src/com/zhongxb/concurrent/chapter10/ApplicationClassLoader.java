package com.zhongxb.concurrent.chapter10;

/**
 * 系统类加载器，其负责加载classpath下的类库资源，其父加载器是扩展类加载器
 */
public class ApplicationClassLoader {

    public static void main(String[] args) {

        System.out.println(System.getProperty("java.class.path"));

        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
