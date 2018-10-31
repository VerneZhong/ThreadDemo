package com.zhongxb.concurrent.chapter24;

import com.zhongxb.concurrent.chapter08.DenyPolicy;
import com.zhongxb.concurrent.chapter08.ThreadPool;
import com.zhongxb.concurrent.chapter08.ThreadPoolBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 处理任务的接线员
 *
 * @author Mr.zxb
 * @date 2018-10-31 17:38
 */
public class Operator {

    /**
     * 初始化线程池
     */
    private final ThreadPool threadPool = new ThreadPoolBuilder().setInitSize(2).setMaxSize(6).setCoreSize(4)
            .setQueueMaxSize(1000).setDenyPolicy(new DenyPolicy.DiscardDenyPolicy())
            .setKeepAliveTime(10).setTimeUnit(TimeUnit.SECONDS).build();

    public void call(String business) {
        // 为每一个请求创建一个线程去处理
        TaskHandler taskHandler = new TaskHandler(new Request(business));
        threadPool.execute(taskHandler);
    }
}
