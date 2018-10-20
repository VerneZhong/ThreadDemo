package com.zhongxb.concurrent.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * 线程组优先级
 */
public class ThradGroupPriority {

    public static void main(String[] args) {

        ThreadGroup group = new ThreadGroup("group1");
        Thread thread = new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");
        thread.setDaemon(true);
        thread.start();

        System.out.println("group.getMaxPriority=" + group.getMaxPriority());
        System.out.println("thread.getPriority=" + thread.getPriority());

        // 改变group最大优先级
        group.setMaxPriority(3);

        Thread thread1 = new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2");
        thread1.setDaemon(true);
        thread1.start();

        // 如果线程优先级大于线程组的优先级情况下
        // 之前加入线程组的优先级不会受到改变，后续加入线程组的线程的优先级和线程组的优先级一样

        System.out.println("group.getMaxPriority=" + group.getMaxPriority());
        System.out.println("thread.getPriority=" + thread.getPriority());
        System.out.println("thread1.getPriority=" + thread1.getPriority());

    }

}
