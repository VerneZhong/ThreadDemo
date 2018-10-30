package com.zhongxb.concurrent.chapter19;

/**
 * 异步任务回调接口
 * @author Mr.zxb
 * @date 2018-10-30 14:37
 */
@FunctionalInterface
public interface Callback<T> {

    /**
     * 任务完成后调用该接口，其中T为任务执行后的结果
     * @param t
     */
    void call(T t);
}
