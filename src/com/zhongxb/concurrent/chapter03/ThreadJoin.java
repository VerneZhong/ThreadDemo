package com.zhongxb.concurrent.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 *  join 会使当前线程进入阻塞状态
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        // 定义两个线程，并保存在threas中
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(toList());

        threads.forEach(Thread::start);

        for (Thread t : threads) {
            t.join();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }

    }

    private static Thread create(int i) {
        return new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(i));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
