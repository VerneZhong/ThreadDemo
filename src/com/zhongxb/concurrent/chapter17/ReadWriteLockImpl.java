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
    private void incrementWritingWriters() {
        this.writingWriters++;
    }

    /**
     * 使等待写入的线程数量增加
     */
    private void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    /**
     * 使读取线程数量增加
     */
    private void incrementReadingReaders() {
        this.readingReaders++;
    }

    /**
     * 使写线程的数量减少
     */
    private void decrementWritingWriters() {
        this.writingWriters--;
    }

    /**
     * 使等待写入的线程数量减少
     */
    private void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    /**
     * 使读取线程数量减少
     */
    private void decrementReadingReaders() {
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

    /**
     * 读锁
     * @author Mr.zxb
     * @date 2018-10-29 14:23
     */
    static class ReadLock implements Lock {

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

    /**
     * 写锁
     * @author Mr.zxb
     * @date 2018-10-29 14:23
     */
    static class WriteLock implements Lock {

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

}
