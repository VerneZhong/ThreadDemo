package com.zhongxb.concurrent.chapter17;

/**
 * 读写锁
 * @author Mr.zxb
 * @date 2018-10-29 13:42
 */
public interface ReadWriteLock {

    /**
     * 创建读取锁
     * @return
     */
    Lock readLock();

    /**
     * 创建写入锁
     * @return
     */
    Lock writeLock();

    /**
     * 获取当前有多少线程正在执行写入操作，最多是1个
     * @return
     */
    int getWritingWriters();

    /**
     * 获取当前有多少线程正在等待获取写入锁
     * @return
     */
    int getWaitingWriters();

    /**
     * 获取当前有多少线程正在执行读取操作
     * @return
     */
    int getReadingReaders();

    /**
     * 工厂方法，创建ReadWriteLock
     * @return
     */
    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    /**
     * 工厂方法，创建ReadWriteLock，并且传入preferWriter
     * @param preferWriter
     * @return
     */
    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
