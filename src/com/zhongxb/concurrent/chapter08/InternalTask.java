package com.zhongxb.concurrent.chapter08;

/**
 * 用于线程池内部，该类会使用到RunnableQueue，不断从任务队列获取任务并执行
 * @author zxb
 */
public class InternalTask implements Runnable {

    private RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        // 如果当前任务为running并且没有被中断，则其将不断地从queue中获取Runnable,然后执行run方法
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前任务，主要会在线程池的shutdown方法中使用
     */
    public void stop() {
        this.running = false;
    }
}
