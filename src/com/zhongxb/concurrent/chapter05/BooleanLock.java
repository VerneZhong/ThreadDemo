package com.zhongxb.concurrent.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

/**
 * Lock的一个Boolean实现，通过控制Boolean变量的开关来决定
 * 是否允许当前的线程获取该锁
 *
 * @author zxb
 */
public class BooleanLock implements Lock {

    /**
     * 当前拥有锁的线程
     */
    private Thread currentThread;
    /**
     * locked为false代表没有被任何线程获得锁或是锁已经释放，
     * 为true就代表被当前线程获取了锁
     */
    private volatile boolean locked = false;
    /**
     * 阻塞线程列表
     */
    private List<Thread> blockThreads = new ArrayList<>();

    /**
     * 锁
     *
     * @throws InterruptedException
     */
    @Override
    public void lock() throws InterruptedException {
        // 同步
        synchronized (this) {
            while (locked) {
                // 当前锁被其他线程获取，使该线程进入阻塞队列
                // 使当前线程wait释放this monitor的所有权
                // 暂存当前线程
                Thread template = currentThread();
                try {
                    if (!blockThreads.contains(template)) {
                        blockThreads.add(template);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    // 如果当前线程在wait时被中断，则从blockThreads中将其删除，避免内存泄漏
                    blockThreads.remove(template);
                    // 继续抛出中断异常
                    throw e;
                }
            }
            // 当前锁未被其他线程获取，删除阻塞队列自己的线程
            blockThreads.remove(currentThread);
            // 锁的开关为打开
            this.locked = true;
            // 记录获取锁的线程
            this.currentThread = currentThread();
        }
    }

    /**
     * 超时锁
     *
     * @param timeout
     * @throws InterruptedException
     * @throws TimeoutException
     * @throws IllegalArgumentException
     */
    @Override
    public void lock(long timeout) throws InterruptedException, TimeoutException, IllegalArgumentException {
        synchronized (this) {
            // 参数不合法抛出异常
            if (timeout <= 0) {
                throw new IllegalArgumentException("参数非法");
            } else {
                long remainingMills = timeout;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked) {
                    // 如果remainingMills小于等于0，则意味着当前线程被其他线程唤醒或者在指定的时间内没有获取到锁，抛出超时异常
                    if (remainingMills <= 0) {
                        throw new TimeoutException("超时");
                    }
                    // 暂存当前线程
                    Thread template = currentThread();
                    try {
                        // 将阻塞线程加入阻塞线程队列
                        if (!blockThreads.contains(template)) {
                            blockThreads.add(template);
                        }
                        this.wait();
                    } catch (InterruptedException e) {
                        // 如果当前线程在wait时被中断，则从blockThreads中将其删除，避免内存泄漏
                        blockThreads.remove(template);
                        // 继续抛出中断异常
                        throw e;
                    }
                    // 等待超时时间
                    this.wait(remainingMills);
                    // 重新计算超时时间
                    remainingMills = endMills - currentTimeMillis();
                }
                // 获得锁
                blockThreads.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    /**
     * 获取当前毫秒数
     *
     * @return
     */
    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        synchronized (this) {
            // 判断当前线程是否是获取锁的线程，只有加锁的线程才可以解锁
            if (currentThread == currentThread()) {
                // 初始化锁
                this.locked = false;
                Optional.of(currentThread().getName() + " release the lock.").ifPresent(System.out::println);
                // 通知其他阻塞线程获取锁
                this.notifyAll();
            }
        }
    }

    /**
     * 阻塞线程队列
     *
     * @return
     */
    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockThreads);
    }

    /**
     * 获取阻塞线程数量
     *
     * @return
     */
    @Override
    public int getBlockedThreadCount() {
        return this.blockThreads.size();
    }
}
