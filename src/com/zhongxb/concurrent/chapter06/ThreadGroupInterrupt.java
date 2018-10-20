package com.zhongxb.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 线程组中断,一个线程组被中断，其子线程组和线程都会被中断
 */
public class ThreadGroupInterrupt {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup group = new ThreadGroup("testGroup");

        Thread thread = new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t1 will exit.");
        }, "thread1");
        thread.setDaemon(true);
        thread.start();

        Thread thread2 = new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t2 will exit.");
        }, "thread2");
        thread2.setDaemon(true);
        thread2.start();

        TimeUnit.MILLISECONDS.sleep(2);

        group.interrupt();
    }
}
