package com.zhongxb.concurrent.chapter23;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-10-31 15:11
 */
public class CountDownLatchJdkTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(4);

        new ProgrammerTravel(latch, "Zxb", "Subway").start();
        new ProgrammerTravel(latch, "Jwb", "Bicycle").start();
        new ProgrammerTravel(latch, "Whb", "Bus").start();
        new ProgrammerTravel(latch, "Xcl", "Walking").start();

//        latch.await();
        latch.await(5, TimeUnit.SECONDS);
        System.out.println("== all of programmer arrived ==");
    }
}
