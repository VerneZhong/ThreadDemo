package com.zhongxb.concurrent.chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 读写锁的使用
 * @author Mr.zxb
 * @date 2018-10-29 16:16
 */
public class ShareData {

    /**
     * 定义共享数据（资源）
     */
    private final List<Character> container = new ArrayList<>();

    /**
     * 构造ReadWriteLock
     */
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    /**
     * 创建读取锁
     */
    private final Lock readLock = readWriteLock.readLock();

    /**
     * 创建写入锁
     */
    private final Lock writeLock = readWriteLock.writeLock();
    
    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add(i, 'c');
        }
    }

    /**
     * 读取数据
     * @return
     * @throws InterruptedException
     */
    public char[] read() throws InterruptedException {
        try {
            // 首次使用读锁进行lock
            readLock.lock();

            char[] newBuffer = new char[length];
            for (int i = 0; i < length; i++) {
                newBuffer[i] = container.get(i);
            }
            slowly();
            return newBuffer;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 写入数据
     * @param c
     * @throws InterruptedException
     */
    public void write(char c) throws InterruptedException {
        try {
            // 使用写锁进行lock
            writeLock.lock();

            for (int i = 0; i < length; i++) {
                this.container.add(i, c);
            }
            slowly();
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 简单模拟操作的耗时
     */
    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
