package com.zhongxb.concurrent.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * 钩子线程
 */
public class ThreadHook {

    public static void main(String[] args) {

        // 给Java程序添加钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The hook thread1 is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread1 will exit.");
        }));

        // 钩子线程可注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The hook thread2 is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread2 will exit.");
        }));


    }
}
