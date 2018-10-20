package com.zhongxb.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 复制线程
 */
public class ThreadGroupEnumeraterThreads {

    public static void main(String[] args) throws InterruptedException {

        // 创建一个ThreadGroup
        ThreadGroup group = new ThreadGroup("myGroup");
        // 创建线程传入threadgroup
        Thread thread = new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "myThread");
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];
        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);
        for (Thread t : list) {
            System.out.println(t.getName());
        }

        Thread[] list1 = new Thread[mainGroup.activeCount() - 1];
        recurseSize = mainGroup.enumerate(list1, false);
        System.out.println(recurseSize);
        for (Thread t : list1) {
            System.out.println(t.getName());
        }


    }
}
