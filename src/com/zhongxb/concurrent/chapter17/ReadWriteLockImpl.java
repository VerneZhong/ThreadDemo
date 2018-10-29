package com.zhongxb.concurrent.chapter17;

/**
 * 读写锁方法类，读写锁适用于读多写少的业务场景
 * @author Mr.zxb
 * @date 2018-10-29 13:46
 */
public class ReadWriteLockImpl implements ReadWriteLock {

    /**
     * 定义对象锁
     */
    private final Object MUTEX = new Object();

    /**
     * 当前有多少个线程正在写入
     */
    private int writingWriters = 0;

    /**
     * 当前有多少个线程等待写入
     */
    private int waitingWriters = 0;

    /**
     * 当前有多少个线程正在read
     */
    private int readingReaders = 0;

    /**
     * read和write的偏好设置
     */
    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    /**
     * 创建读取锁
     * @return
     */
    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    /**
     * 创建写入锁
     * @return
     */
    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    /**
     * 使写线程的数量增加
     */
    public void incrementWritingWriters() {
        this.writingWriters++;
    }

    /**
     * 使等待写入的线程数量增加
     */
    public void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    /**
     * 使读取线程数量增加
     */
    public void incrementReadingReaders() {
        this.readingReaders++;
    }

    /**
     * 使写线程的数量减少
     */
    public void decrementWritingWriters() {
        this.writingWriters--;
    }

    /**
     * 使等待写入的线程数量减少
     */
    public void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    /**
     * 使读取线程数量减少
     */
    public void decrementReadingReaders() {
        this.readingReaders--;
    }

    /**
     * 获取当前有多少线程正在执行写入操作，最多是1个
     * @return
     */
    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    /**
     * 获取当前有多少线程正在等待获取写入锁
     * @return
     */
    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    /**
     * 获取当前有多少线程正在执行读取操作
     * @return
     */
    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    /**
     * 获得对象锁
     * @return
     */
    public Object getMUTEX() {
        return this.MUTEX;
    }

    /**
     * 获取当前是否偏向写锁
     * @return
     */
    public boolean getPreferWriter() {
        return preferWriter;
    }

    /**
     * 设置写锁偏好
     * @param preferWriter
     */
    public void setPreferWriter(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
