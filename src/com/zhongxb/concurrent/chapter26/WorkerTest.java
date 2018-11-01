package com.zhongxb.concurrent.chapter26;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Mr.zxb
 * @date 2018-11-01 15:27
 */
public class WorkerTest {

    public static void main(String[] args) {

        // 流水线上有5个工人
        final ProductionChannel channel = new ProductionChannel(5);
        AtomicInteger productionNo = new AtomicInteger();
        // 流水线上有8个工作人员往传送带上不断地放置等待加工的半成品
        IntStream.range(0, 8).forEach(i -> {
            new Thread(() -> {
               while (true) {
                   channel.offerProduction(new Production(productionNo.getAndIncrement()));
                   try {
                       TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }).start();
        });
    }
}
