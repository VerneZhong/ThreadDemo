package com.zhongxb.concurrent.chapter01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试创建线程的数量
 */
public class ThreadConstruction1 extends Thread {

    public final static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {

        try {
            while (true) {
                new ThreadConstruction1().start();
            }
        } catch (Throwable e) {
            System.out.println("failed At==>" + counter.get());
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("The " + counter.getAndIncrement() + " thread be created.");
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
