package com.zhongxb.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程组,设置了守护线程组的在没有active线程时，该ThreadGroup会被销毁
 */
public class ThreadGroupDaemon {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup group = new ThreadGroup("testGroup");
        new Thread(group, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "testGroup-thread1").start();

        ThreadGroup group2 = new ThreadGroup("Group2");
        new Thread(group2, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group2-thread2").start();

        // 设置daemon为true
        group2.setDaemon(true);

        TimeUnit.SECONDS.sleep(3);
        System.out.println(group.isDestroyed());
        System.out.println(group2.isDestroyed());
    }
}
