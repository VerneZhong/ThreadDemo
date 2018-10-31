package com.zhongxb.concurrent.chapter22;

/**
 * Balking设计：多个线程监控某个共享变量，A线程监控到共享变量发生变化后即触发某个条件，
 * 但是有其他线程已经对该变量开始了行动，就放弃准备开始工作
 * @author Mr.zxb
 * @date 2018-10-31 11:32
 */
public class BalkingTest {

    public static void main(String[] args) {

        new DocumentEditThread("D:\\", "test.txt").start();
    }
}
