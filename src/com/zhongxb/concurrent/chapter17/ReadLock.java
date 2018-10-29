package com.zhongxb.concurrent.chapter17;

/**
 * 读锁
 * @author Mr.zxb
 * @date 2018-10-29 14:23
 */
public class ReadLock implements Lock {

    private ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        // 使用MUTEX作为锁
        synchronized (readWriteLock.getMUTEX()) {
            // 若此时有线程在进行写操作，或者有写线程在等待并且偏向写锁的标识为true时，就会无法获得读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0 || (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMUTEX().wait();
            }
            // 成功获得读锁，并且使readingReaders的数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        // 使用MUTEX作为锁
        synchronized (readWriteLock.getMUTEX()) {
            // 释放锁的过程就是使得当前reading的数量减1
            readWriteLock.decrementReadingReaders();
            // 将preferWriter设置为true，可以使得writer线程获得更多的机会
            readWriteLock.setPreferWriter(true);
            // 通知唤醒与MUTEX关联的monitor waitset中的线程
            readWriteLock.getMUTEX().notifyAll();

        }
    }
}
