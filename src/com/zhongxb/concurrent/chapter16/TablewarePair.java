package com.zhongxb.concurrent.chapter16;

/**
 * 将多个交叉锁，封装成一个对象
 * @author Mr.zxb
 * @date 2018-10-29 11:25
 */
public class TablewarePair {

    /**
     * 左手边的餐具
     */
    private final Tableware leftTool;

    /**
     * 右手边的餐具
     */
    private final Tableware rightTool;

    public TablewarePair(Tableware leftTool, Tableware rightTool) {
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public Tableware getLeftTool() {
        return leftTool;
    }

    public Tableware getRightTool() {
        return rightTool;
    }
}
