package com.zhongxb.concurrent.chapter15;

@FunctionalInterface
public interface Task<T> {

    /**
     * 任务执行并返回信息
     * @return
     */
    T call();

}
