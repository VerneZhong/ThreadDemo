package com.zhongxb.concurrent.chapter08;

/**
 * 任务队列，主要用于缓存提交到线程池中的任务
 * 存放提交的Runnable，该Runnable是个BlockedQueue，并且有limit限制，防止内存溢出
 * @author zxb
 */
public interface RunnableQueue {

    /**
     * 当有新任务进来时首先会offer入队列
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程通过take方法获取Runnable
     * @return
     * @throws InterruptedException
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中任务的数量
     * @return
     */
    int size();
}
