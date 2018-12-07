package com.zhongxb.concurrent.chapter23;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-10-31 14:12
 */
public class ProgrammerTravel extends Thread {

    /**
     * 门阀
     */
    private Latch latch;

    private CountDownLatch countDownLatch;

    /**
     * 程序员
     */
    private final String programmer;

    /**
     * 交通工具
     */
    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    public ProgrammerTravel(CountDownLatch countDownLatch, String programmer, String transportation) {
        this.countDownLatch = countDownLatch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation [" + transportation + "]");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " already by " + transportation);
        // 完成任务时使计数器减1
        if (latch != null) {
            // 自定义的CountDownLatch
            latch.countDown();
        } else {
            // jdk的CountDownLatch
            countDownLatch.countDown();
        }
    }
}
