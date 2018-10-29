package com.zhongxb.concurrent.chapter17;

/**
 * 写锁
 * @author Mr.zxb
 * @date 2018-10-29 14:23
 */
public class WriteLock implements Lock {

    private ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                //首次使等待获取写入的锁数量加1
                readWriteLock.incrementWaitingWriters();
                // 如果此时有其他线程正在进行读操作，或者写操作，那么当前线程将被挂起
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                // 成功获取到了写入锁，使得等待获取写入锁的计数器减1
                readWriteLock.decrementWritingWriters();
            }
            // 获得写锁，将正在写入的线程加1
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            // 减少正在写入锁的线程计数器
            readWriteLock.decrementWritingWriters();
            // 将偏好状态修改为false，可以使得读锁被最快速获得
            readWriteLock.setPreferWriter(false);
            // 通知唤醒其他在Mutex monitor waitset 中的线程
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
