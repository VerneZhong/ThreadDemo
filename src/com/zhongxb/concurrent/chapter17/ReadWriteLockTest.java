package com.zhongxb.concurrent.chapter17;

import static java.lang.Thread.currentThread;

/**
 * ReadWriteLock test
 * @author Mr.zxb
 * @date 2018-10-29 16:33
 */
public class ReadWriteLockTest {

    public static final String text = "Thisistheexampleforreadwritelock";

    public static void main(String[] args) {

        // 定义共享数据
        ShareData shareData = new ShareData(50);

        // 创建2个线程写操作
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < text.length(); j++) {
                    try {
                        char c = text.charAt(j);
                        shareData.write(c);
                        System.out.println(currentThread() + " write " + c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "WriteThread").start();
        }

        // 创建10个线程进行读操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(currentThread() + " read " + new String(shareData.read()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "ReadThread").start();
        }
    }
}
