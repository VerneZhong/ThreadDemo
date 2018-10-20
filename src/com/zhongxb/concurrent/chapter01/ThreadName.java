package com.zhongxb.concurrent.chapter01;

import java.util.stream.IntStream;

/**
 * 线程命名
 */
public class ThreadName {

    public static final String PREFIX = "ALEX-";

    public static void main(String[] args) {

        // boxed 将数值流转化为 Stream
//        IntStream.range(0, 5).mapToObj(i -> new Thread(() -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);
//        IntStream.range(0, 5).boxed().map(i -> new Thread(() -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);

        IntStream.range(0, 5).mapToObj(ThreadName::createThread).forEach(Thread::start);

    }

    private static Thread createThread(final int intName) {
        return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
    }
}
