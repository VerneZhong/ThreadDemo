package com.zhongxb.concurrent.chapter16;

/**
 * 案列吃面，引起的死锁
 * @author Mr.zxb
 * @date 2018-10-29 11:07
 */
public class Tableware {

    /**
     * 餐具名称
     */
    private final String toolName;

    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tool：" + toolName;
    }
}
