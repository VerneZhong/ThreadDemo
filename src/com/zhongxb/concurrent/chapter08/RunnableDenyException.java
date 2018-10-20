package com.zhongxb.concurrent.chapter08;

/**
 * 通知任务提交者，无法再接收新的任务
 */
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String s) {
        super(s);
    }
}
