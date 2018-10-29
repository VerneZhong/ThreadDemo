package com.zhongxb.concurrent.chapter16;

/**
 * Single Thread Execution 设计模式：指的是在同一时刻只能有一个线程去访问共享资源
 * 非线程安全- 模拟机场安检
 * synchronized保证排他性，保证线程安全
 * @author Mr.zxb
 * @date 2018-10-29 10:41
 */
public class FlightSecurity {

    private int count = 0;

    /**
     * 登机牌
     */
    private String boardingPass = "";

    /**
     * 身份证
     */
    private String idCard = "";

    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        this.check();
        System.out.println("安检通过.");
    }

    private void check() {
        // 简单测试，当登机牌和身份证首字母不同时，则表示不通过
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("====Exception====" + toString());
        }
    }

    @Override
    public String toString() {
        return "The " + count + " passengers, boardingPass [" + boardingPass + "]," + "[" + idCard + "]";
    }
}
