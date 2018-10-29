package com.zhongxb.concurrent.chapter16;

/**
 * 吃面条的线程
 * 交叉锁导致的线程死锁：解决思路是将多个锁进行封装，使其是一个类
 *
 * @author Mr.zxb
 * @date 2018-10-29 11:10
 */
public class EatNoodleThread extends Thread {

    private final String name;

    /**
     * 左手边的餐具
     */
//    private final Tableware leftTool;

    /**
     * 右手边的餐具
     */
//    private final Tableware rightTool;

    private final TablewarePair tablewarePair;

    public EatNoodleThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    /**
     * 吃面条的过程
     */
    private void eat() {
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + " (left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + " (right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + " (right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + " (left)");
        }
    }
}
