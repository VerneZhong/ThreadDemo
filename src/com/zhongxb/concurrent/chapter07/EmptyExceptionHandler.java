package com.zhongxb.concurrent.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * 未设置exception handler,会调用所在ThreadGroup的父handler
 */
public class EmptyExceptionHandler {

    public static void main(String[] args) {

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // here will throw unchecked exception.
            System.out.println(1 / 0);
        }, "test-thread");
        thread.start();

    }
}
