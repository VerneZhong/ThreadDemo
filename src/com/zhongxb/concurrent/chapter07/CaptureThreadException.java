package com.zhongxb.concurrent.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * 线程运行时异常处理
 */
public class CaptureThreadException {

    public static void main(String[] args) {

        // 1.设置回调接口
        Thread.setDefaultUncaughtExceptionHandler(new CustomThreadException());

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 2. 这里出现unchecked异常
            System.out.println(1 / 0);
        }, "test-thread");

        thread.start();
    }
}
