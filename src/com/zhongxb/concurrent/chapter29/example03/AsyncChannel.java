package com.zhongxb.concurrent.chapter29.example03;

import com.zhongxb.concurrent.chapter29.example02.Channel;
import com.zhongxb.concurrent.chapter29.example02.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 该类为一个基类，提供Message的并发处理能力
 * @author Mr.zxb
 * @date 2018-11-07 16:33
 */
public abstract class AsyncChannel implements Channel<Event> {

    /**
     * 在AsyncChannel中将使用ExecutorService多线程的方式提交给Message
     */
    private final ExecutorService executorService;

    /**
     * 默认构造函数，提供了CPU的核数 * 2的线程数量
     */
    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    /**
     * 用户自定义的ExecutorService
     * @param executorService
     */
    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * 重写dispatch方法，用final修饰，避免子类重写
     * @param message
     */
    @Override
    public final void dispatch(Event message) {
        executorService.submit(() -> this.handle(message));
    }

    /**
     * 提供抽象方法，供子类实现具体的Message处理
     * @param message
     */
    protected abstract void handle(Event message);

    /**
     * 提供关闭ExecutorService的方法
     */
    public void stop() {
        if (null != executorService && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
