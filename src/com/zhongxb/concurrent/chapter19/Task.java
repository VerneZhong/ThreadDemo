package com.zhongxb.concurrent.chapter19;

/**
 * 主要是提供调用者实现计算逻辑用的
 * @author Mr.zxb
 * @date 2018-10-30 11:33
 */
@FunctionalInterface
public interface Task<T, R> {

    /**
     * 给定一个参数，经过计算返回结果
     * @param input
     * @return
     */
    R get(T input);
}
