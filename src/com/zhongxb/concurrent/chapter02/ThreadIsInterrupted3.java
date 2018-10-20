package com.zhongxb.concurrent.chapter02;

import java.util.concurrent.TimeUnit;

/**
 * isInterrupted() 不会改变中断标识
 * interrupted() 会擦除中断标识
 */
public class ThreadIsInterrupted3 {

    public static void main(String[] args) {

        // 1判断当前线程是否被中断
        System.out.printf("Main thread is interrupted ? %s\n", Thread.interrupted());

        // 2中断当前线程
        Thread.currentThread().interrupt();

        // 3判断当前线程是否被中断
        System.out.printf("Main thread is interrupted ? %s\n", Thread.currentThread().isInterrupted());

        try {
            // 4当前线程执行可中断方法
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            // 5捕获中断信号
            System.out.println("I will be interrupted still.");
        }

    }
}
