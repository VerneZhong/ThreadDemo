package com.zhongxb.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 标识加中断，退出线程
 */
public class FlagThreadExit {

    static class MyTask extends Thread {

        /**
         * 开关变量
         */
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work.");
            while (!closed && !isInterrupted()) {
                // 正在运行
            }
            System.out.println("I will be exiting.");
        }

        public void close() {
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyTask task = new MyTask();
        task.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("System will be shutdown.");
        task.close();
    }
}
