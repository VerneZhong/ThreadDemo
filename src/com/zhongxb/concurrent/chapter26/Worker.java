package com.zhongxb.concurrent.chapter26;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 工人线程
 * @author Mr.zxb
 * @date 2018-11-01 14:57
 */
public class Worker extends Thread {

    private final ProductionChannel channel;

    /**
     * 主要用于获取一个随机值，模拟加工一个产品需要耗费一定的时间，当然每个工人操作时所花费的时间也可能不一样
     */
    private static final Random random = new Random(System.currentTimeMillis());

    public Worker(String workName, ProductionChannel productionChannel) {
        super(workName);
        this.channel = productionChannel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 从传送带上获取产品
                Production production = channel.takeProduction();
                System.out.println(getName() + " process the " + production);
                // 对产品进行加工
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
