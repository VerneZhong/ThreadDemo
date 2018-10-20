package com.zhongxb.concurrent.chapter02;

import java.util.concurrent.TimeUnit;

/**
 * 线程打断
 */
public class ThreadInterupt {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Oh, I am be interrupted.");
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();

    }
}
