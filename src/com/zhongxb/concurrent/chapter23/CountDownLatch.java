package com.zhongxb.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-10-31 14:02
 */
public class CountDownLatch extends Latch {
    /**
     * 通过构造传入limit
     *
     * @param limit
     */
    public CountDownLatch(int limit) {
        super(limit);
    }

    public CountDownLatch(int limit, Runnable runnable) {
        super(limit, runnable);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            // 当limit>0时，当前线程进入阻塞状态
            while (limit > 0) {
                this.wait();
            }
        }
        if (null != runnable) {
            runnable.run();
        }
    }

    @Override
    public void await(long time, TimeUnit timeUnit) throws InterruptedException, WaitTimeoutException {
        if (time < 0) {
            throw new IllegalArgumentException("The time is invalid.");
        }
        // 将time转换为纳秒
        long remainingNanos = timeUnit.toNanos(time);
        // 等待任务将在endNanos纳秒后超时
        final long endNanos = System.nanoTime() + remainingNanos;
        synchronized (this) {
            while (limit > 0) {
                // 如果超时则抛出WaitTimeoutException异常
                if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0) {
                    throw new WaitTimeoutException("The wait time over specify time.");
                }
                // 等待remainingNanos，在等待的过程中有可能会被中断，需要重新计算remainingNanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
        if (null != runnable) {
            runnable.run();
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived.");
            }
            // 使limit减1，并且通知阻塞线程
            limit--;
            this.notifyAll();

        }
    }

    @Override
    public int getUnarrived() {
        return limit;
    }

}
