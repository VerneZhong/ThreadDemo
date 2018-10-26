package com.zhongxb.concurrent.chapter15;

public interface Observable {

    /**
     * 任务生命周期的枚举类型
     */
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    /**
     * 获取当前任务的生命周期状态
     * @return
     */
    Cycle getCycle();

    /**
     * 定义启动线程的方法，主要作用是为了屏蔽Thread方法
     */
    void start();

    /**
     * 定义线程的中断方法，作用也是为了屏蔽Thread方法
     */
    void interrupt();
}
