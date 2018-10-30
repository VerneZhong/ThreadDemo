package com.zhongxb.concurrent.chapter20;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-10-30 15:11
 */
public class GuardedSuspensionQueueTest {

    public static void main(String[] args) {

        GuardedSuspensionQueue guardedSuspensionQueue = new GuardedSuspensionQueue();

        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    guardedSuspensionQueue.offer(i);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者").start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                    System.out.println(guardedSuspensionQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();
    }
}
