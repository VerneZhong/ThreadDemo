package com.zhongxb.concurrent.chapter02;

/**
 * 当前线程
 */
public class CurrentThread {

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();

        String name = Thread.currentThread().getName();
        System.out.println(name);
    }
}
