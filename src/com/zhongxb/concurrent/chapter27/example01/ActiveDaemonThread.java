package com.zhongxb.concurrent.chapter27.example01;

import com.zhongxb.concurrent.chapter27.example01.ActiveMessageQueue;
import com.zhongxb.concurrent.chapter27.example01.MethodMessage;

/**
 * 该类是个守护线程，主要是从队列中获取Message然后执行execute方法
 * @author Mr.zxb
 * @date 2018-11-01 16:38
 */
public class ActiveDaemonThread extends Thread {

    private final ActiveMessageQueue<MethodMessage> queue;

    public ActiveDaemonThread(ActiveMessageQueue activeMessageQueue) {
        super("ActiveDaemonThread");
        this.queue = activeMessageQueue;
        // 设置为守护线程
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            // 从队列中获取一个MethodMessage，然后执行execute方法
            MethodMessage methodMessage = this.queue.take();
            methodMessage.execute();
        }
    }
}
