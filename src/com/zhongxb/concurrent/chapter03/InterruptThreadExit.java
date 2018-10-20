package com.zhongxb.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断退出
 */
public class InterruptThreadExit {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("I will start work.");
                while (!isInterrupted()) {
//                    System.out.println("I's working.");
                }
                System.out.println("I will be exiting.");
            }
        };

        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("System will be shutdown.");
        thread.interrupt();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    System.out.println("I will start work.");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("I will be exiting.");
            }
        };
        t1.start();
        t1.interrupt();
    }
}
