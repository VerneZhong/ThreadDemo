package com.zhongxb.concurrent.chapter08;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {

        //定义线程池，初始化线程为2，核心线程4，最大线程6，任务队列最大允许1000个
        ThreadPool threadPool = new ThreadPoolBuilder().setInitSize(2)
                .setCoreSize(4).setMaxSize(6).setQueueMaxSize(1000)
                .setDenyPolicy(new DenyPolicy.DiscardDenyPolicy())
                .setKeepAliveTime(10).setTimeUnit(TimeUnit.SECONDS).build();

        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (true) {
            System.out.println("getActiveCount:" + threadPool.getActiveCount());
            System.out.println("getQueueSize:" + threadPool.getQueueSize());
            System.out.println("getCoreSize:" + threadPool.getCoreSize());
            System.out.println("getMaxSize:" + threadPool.getMaxSize());
            System.out.println("--------------------------------");
            TimeUnit.SECONDS.sleep(5);
//
//            threadPool.shutdown();
//            Thread.currentThread().join();
        }

    }
}
