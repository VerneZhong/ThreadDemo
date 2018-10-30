package com.zhongxb.concurrent.chapter19;

/**
 * Future的实现
 * @author Mr.zxb
 * @date 2018-10-30 12:43
 */
public class FutureTask<T> implements Future<T> {

    /**
     * 计算结果
     */
    private T result;

    /**
     * 任务是否完成
     */
    private boolean isDone = false;

    /**
     * 定义对象锁
     */
    private final Object lock = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (lock) {
            // 当任务还没有完成时，调用get方法会被挂起而进入阻塞
            while (!isDone) {
               lock.wait();
            }
            // 任务完成返回最终结果
            return result;
        }
    }

    /**
     * 主要用于为FutureTask设置计算结果
     * @param result
     */
    protected void finish(T result) {
        synchronized (lock) {
            // balking设计模式，如果任务已完成直接返回
            if (isDone) {
                return;
            }
            // 计算完成，为result指定结果，并且将isDone设为true，同时唤醒阻塞中的线程
            this.result = result;
            this.isDone = true;
            lock.notifyAll();
        }
    }

    /**
     * 返回当前任务是否已经完成
     * @return
     */
    @Override
    public boolean done() {
        return isDone;
    }
}
