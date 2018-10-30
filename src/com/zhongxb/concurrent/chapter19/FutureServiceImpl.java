package com.zhongxb.concurrent.chapter19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主要作用在于当任务提交时创建一个新的线程来受理该任务，
 * 进而达到任务异步执行的效果
 *
 * @author Mr.zxb
 * @date 2018-10-30 12:40
 */
public class FutureServiceImpl<T, R> implements FutureService<T, R> {

    /**
     * 为执行的线程指定名称前缀
     */
    public static final String FUTURE_THREAD_PREFIX = "Future-";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            // 任务执行完成之后将null作为结果传给future
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<R> submit(Task<T, R> task, T input) {
        final FutureTask<R> futureTask = new FutureTask<>();
        new Thread(() -> {
            R result = task.get(input);
            // 任务执行完成之后将result作为结果传给future
            futureTask.finish(result);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<R> submit(Task<T, R> task, T input, Callback<R> callback) {
        final FutureTask<R> futureTask = new FutureTask<>();
        new Thread(() -> {
            R result = task.get(input);
            futureTask.finish(result);
            // 执行回调接口
            if (callback != null) {
                callback.call(result);
            }
        }, getNextName()).start();
        return futureTask;
    }
}
