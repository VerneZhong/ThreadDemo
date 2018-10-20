package com.zhongxb.concurrent.chapter05;

import java.util.concurrent.TimeUnit;

public class EventClient {

    public static void main(String[] args) {

        EventQueue eventQueue = new EventQueue();

        new Thread(() -> {
            while (true) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();

        new Thread(() -> {
            while (true) {
                eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();


    }
}
