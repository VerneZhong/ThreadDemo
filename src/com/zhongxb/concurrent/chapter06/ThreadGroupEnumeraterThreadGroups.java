package com.zhongxb.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 复制ThreadGroup
 */
public class ThreadGroupEnumeraterThreadGroups {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup group1 = new ThreadGroup("myGroup1");
        ThreadGroup group2 = new ThreadGroup(group1, "myGroup2");

        TimeUnit.MILLISECONDS.sleep(2);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup[] list = new ThreadGroup[mainGroup.activeGroupCount()];
        ThreadGroup[] list1 = new ThreadGroup[mainGroup.activeGroupCount()];
        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);
        mainGroup.list();

        recurseSize = mainGroup.enumerate(list1, false);
        System.out.println(recurseSize);

    }
}
