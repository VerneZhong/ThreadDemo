package com.zhongxb.concurrent.chapter19;

/**
 * 该接口主要用于提交任务，提交任务主要有两种：
 * 第一种不需要返回值，第二种则需要获得最终的计算结果
 * @author Mr.zxb
 * @date 2018-10-30 11:31
 */
public interface FutureService<T, R> {

    /**
     * 提交不需要返回值的任务
     * @param runnable
     * @return
     */
    Future<?> submit(Runnable runnable);

    /**
     * 提交需要返回值的任务，其中task代替runnable
     * @param task
     * @param input
     * @return
     */
    Future<R> submit(Task<T, R> task, T input);

    /**
     * 增加回调接口Callback，当任务执行结束后，Callback会得到执行
     * @param task      执行的任务接口
     * @param input     参数
     * @param callback  回调接口
     * @return
     */
    Future<R> submit(Task<T, R> task, T input, Callback<R> callback);

    /**
     * 使用静态方法创建一个FutureService实例
     * @param <T>
     * @param <R>
     * @return
     */
    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
