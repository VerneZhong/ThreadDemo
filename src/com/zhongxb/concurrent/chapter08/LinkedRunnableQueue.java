package com.zhongxb.concurrent.chapter08;

import java.util.LinkedList;

/**
 *
 */
public class LinkedRunnableQueue implements RunnableQueue {

    /**
     * 任务队列的最大容量
     */
    private final int limit;

    /**
     * 若任务队列中的任务已经满了，则需要执行策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务队列
     */
    private final LinkedList<Runnable> runnables = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     * 当有新任务进来时首先会offer入队列
     *
     * @param runnable
     */
    @Override
    public void offer(Runnable runnable) {
        synchronized (runnables) {
            if (runnables.size() >= limit) {
                // 无法容纳新的任务队列时执行拒绝策略
                denyPolicy.reject(runnable, threadPool);
            } else {
                // 将任务加入到队尾，并且唤醒阻塞中的线程
                runnables.addLast(runnable);
                runnables.notifyAll();
            }
        }
    }

    /**
     * 工作线程通过take方法获取Runnable
     *
     * @return
     */
    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnables) {
            while (runnables.isEmpty()) {
                try {
                    // 如果任务队列中没有可执行任务，则当前线程将会挂起，进入runnables关联的monitor wait set中等待唤醒（新的任务加入）
                    runnables.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            // 返回当前任务队列中的任务数
            return runnables.removeFirst();
        }
    }

    /**
     * 获取任务队列中任务的数量
     *
     * @return
     */
    @Override
    public int size() {
        synchronized (runnables) {
            return this.runnables.size();
        }
    }
}
