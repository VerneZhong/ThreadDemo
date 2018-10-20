package com.zhongxb.concurrent.chapter01;

/**
 * 守护进程，跟随主进程一起消亡
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "非守护线程");

        // 开启守护线程，跟随jvm进程一起消亡
        t.setDaemon(true);

        t.start();

        Thread.sleep(2_000L);
        System.out.println("main thread finished");
    }
}
