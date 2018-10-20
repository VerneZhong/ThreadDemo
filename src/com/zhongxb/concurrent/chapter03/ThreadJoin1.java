package com.zhongxb.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadJoin1 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int i = 0;
            while (i < 50) {
                System.out.println(i);
                i++;
            }
        });
        thread.setDaemon(true);
        thread.start();

//        TimeUnit.MILLISECONDS.sleep(2);
        thread.join();
        System.out.println("main method start.");


    }
}
