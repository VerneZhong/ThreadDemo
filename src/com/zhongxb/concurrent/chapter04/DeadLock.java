package com.zhongxb.concurrent.chapter04;

import static java.lang.Thread.currentThread;

/**
 * 死锁案列1：多个锁交叉导致死锁，A等待B释放所持有的锁，B等待A释放所持有的锁
 */
public class DeadLock {

    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WRITE = new Object();

    public void read() {
        synchronized (MUTEX_READ) {
            System.out.println(currentThread().getName() + "get READ lock");
            synchronized (MUTEX_WRITE) {
                System.out.println(currentThread().getName() + "get WRITE lock");
            }
            System.out.println(currentThread().getName() + "release WRITE lock");
        }
        System.out.println(currentThread().getName() + "release READ lock");
    }

    public void write() {
        synchronized (MUTEX_WRITE) {
            System.out.println(currentThread().getName() + "get WRITE lock");
            synchronized (MUTEX_READ) {
                System.out.println(currentThread().getName() + "get READ lock");
            }
            System.out.println(currentThread().getName() + "release READ lock");
        }
        System.out.println(currentThread().getName() + "release WRITE lock");
    }

    public static void main(String[] args) {

        DeadLock lock = new DeadLock();
        new Thread(() -> {
            while (true) {
                lock.read();
            }
        }, "Read-Thread-").start();
        new Thread(() -> {
            while (true) {
                lock.write();
            }
        }, "Write-Thread-").start();

    }

}
