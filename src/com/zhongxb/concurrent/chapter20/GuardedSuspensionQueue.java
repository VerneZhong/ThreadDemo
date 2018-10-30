package com.zhongxb.concurrent.chapter20;

import java.util.LinkedList;

/**
 * Guarded Suspension 示例
 * Guarded Suspension关注点在于临界值的条件是否满足，当达到设置的临界值时相关线程则会被挂起
 * @author Mr.zxb
 * @date 2018-10-30 15:04
 */
public class GuardedSuspensionQueue {

    /**
     * 存放Integer类型的队列
     */
    private final LinkedList<Integer> queue = new LinkedList<>();

    /**
     * 队列最大容量为100
     */
    private final int MAX_LIMIT = 100;

    public void offer(Integer data) throws InterruptedException {
        synchronized (this) {
            // 判断queue队列是否超过最大容量
            if (queue.size() >= MAX_LIMIT) {
                // 挂起当前线程，使其陷入阻塞
                this.wait();
            }
            // 插入元素并且唤醒take线程
            queue.addLast(data);
            this.notifyAll();
        }
    }

    /**
     * 从队列中获取元素，如果队列此时为空，则会使当前线程阻塞
     * @return
     * @throws InterruptedException
     */
    public Integer take() throws InterruptedException {
        synchronized (this) {
            // 如果队列为空，则挂起当前线程
            if (queue.isEmpty()) {
                this.wait();
            }
            // 通知offer线程可以继续插入数据了
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
