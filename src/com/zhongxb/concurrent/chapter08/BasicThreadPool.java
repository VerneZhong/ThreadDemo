package com.zhongxb.concurrent.chapter08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author zxb
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    /**
     * 初始化线程的数量
     */
    private int initSize;

    /**
     * 线程池最大线程数量
     */
    private int maxSize;

    /**
     * 线程池核心线程的数量
     */
    private int coreSize;

    /**
     * 当前活跃线程的数量
     */
    private int activeCount;

    /**
     * 线程工厂
     */
    private ThreadFactory threadFactory;

    /**
     * 任务队列
     */
    private RunnableQueue runnableQueue;

    /**
     * 线程池是否已经被关闭
     */
    private volatile boolean isShutdown = false;

    /**
     * 工作线程队列
     */
    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();

    private long keepAliveTime;

    private TimeUnit timeUnit;

    public BasicThreadPool(ThreadPoolBuilder builder) {
        this.initSize = builder.getInitSize();
        this.maxSize = builder.getMaxSize();
        this.coreSize = builder.getCoreSize();
        this.threadFactory = new DefaultThreadFactory();
        this.runnableQueue = new LinkedRunnableQueue(builder.getQueueMaxSize(), builder.getDenyPolicy(), this);
        this.keepAliveTime = builder.getKeepAliveTime();
        this.timeUnit = builder.getTimeUnit();
        this.init();
    }

    /**
     * 初始化时，先创建initSize个线程
     */
    private void init() {
        start();
        for (int i = 0; i < this.initSize; i++) {
            newThread();
        }
    }

    /**
     * 创建线程并启动
     */
    private void newThread() {
        // 创建 runnable 任务
        InternalTask internalTask = new InternalTask(this.runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        // 添加线程任务到任务队列
        threadTaskQueue.offer(threadTask);
        // 活跃线程+1
        this.activeCount++;
        thread.start();
    }

    /**
     * 移除线程
     */
    private void removeThread() {
        ThreadTask threadTask = threadTaskQueue.remove();
        threadTask.getInternalTask().stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        // 用于维护线程数量，比如扩容，回收等工作
        while (!isShutdown() && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if (isShutdown()) {
                    break;
                }
                // 当前的队列中有任务尚未处理
                if (runnableQueue.size() > 0) {
                    // 活跃线程少于核心线程则继续扩容
                    if (activeCount < coreSize) {
                        for (int i = initSize; i < coreSize; i++) {
                            newThread();
                        }
                        // continue的目的在于不想让线程扩容达到maxsize;
                        continue;
                    }
                    // 活跃线程少于最大线程则继续扩容
                    if (activeCount < maxSize) {
                        for (int i = coreSize; i < maxSize; i++) {
                            newThread();
                        }
                    }
                }
                // 任务队列没有任务，则需要回收，回收至核心线程数量
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    /**
     * 提交任务
     *
     * @param runnable
     */
    @Override
    public void execute(Runnable runnable) {
        if (isShutdown()) {
            throw new IllegalStateException("The thread pool is destroy.");
        }
        // 往任务队列里插入runnable
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown()) {
                return;
            }
            isShutdown = true;
            threadTaskQueue.forEach(threadTask -> {
                threadTask.getInternalTask().stop();
                threadTask.getThread().interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        checkActive();
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        checkActive();
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        checkActive();
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        checkActive();
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private void checkActive() {
        if (isShutdown()) {
            throw new IllegalStateException("The thread pool is destroy.");
        }
    }

}
