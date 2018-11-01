package com.zhongxb.concurrent.chapter24.example01;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * TaskHandler用于处理每一个提交的Request请求，由于TaskHandler将被Thread执行，因此需要实现Runnable接口
 * @author Mr.zxb
 * @date 2018-10-31 17:36
 */
public class TaskHandler implements Runnable {

    /**
     * 需要处理的请求
     */
    private final Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Begin handle " + request);
        slowly();
        System.out.println("End handle " + request);
    }

    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
