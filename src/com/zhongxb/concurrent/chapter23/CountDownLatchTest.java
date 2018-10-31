package com.zhongxb.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-10-31 14:15
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        // 定义latch，limit为4
        Latch latch = new CountDownLatch(4);

        new ProgrammerTravel(latch, "Zxb", "Subway").start();
        new ProgrammerTravel(latch, "Jwb", "Bicycle").start();
        new ProgrammerTravel(latch, "Whb", "Bus").start();
        new ProgrammerTravel(latch, "Xcl", "Walking").start();

        // 当前线程
//        latch.await();
        try {
            latch.await(5, TimeUnit.SECONDS);
            System.out.println("== all of programmer arrived ==");
        } catch (WaitTimeoutException e) {
            e.printStackTrace();
        }
    }
}
