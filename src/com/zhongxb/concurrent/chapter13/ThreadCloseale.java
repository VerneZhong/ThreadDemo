package com.zhongxb.concurrent.chapter13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadCloseale extends Thread {

    public ThreadCloseale() {
    }

    public ThreadCloseale(String name) {
        super(name);
    }

    /**
     * 变量在线程里可见性
     */
    private boolean started = true;
    private static final AtomicInteger i = new AtomicInteger(0);

    @Override
    public void run() {
        while (started) {
            System.out.println(i.getAndIncrement());
            try {
                TimeUnit.SECONDS.sleep(10);
                shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.started = false;
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new ThreadCloseale("线程" + i).start();
        }

//        TimeUnit.SECONDS.sleep(5);
//        new ThreadCloseale().shutdown();
    }
}
