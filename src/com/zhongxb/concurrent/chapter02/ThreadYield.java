package com.zhongxb.concurrent.chapter02;

import java.util.stream.IntStream;

/**
 * 线程礼让，不确保CPU会执行线程通知
 */
public class ThreadYield {

    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYield::create).forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() -> {
            if (index == 0) {
                Thread.yield();
            }
            System.out.println(index);
        });
    }
}
