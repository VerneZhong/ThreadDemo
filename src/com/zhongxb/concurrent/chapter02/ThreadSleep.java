package com.zhongxb.concurrent.chapter02;

/**
 * 线程休眠
 */
public class ThreadSleep {

    public static void main(String[] args) {

        new Thread(() -> {
            long start = System.currentTimeMillis();
            sleep(2000L);
            long end = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms", (end - start)));
        }).start();

        long start = System.currentTimeMillis();
        sleep(3000L);
        long end = System.currentTimeMillis();
        System.out.println(String.format("Main method %d ms", (end - start)));

    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
