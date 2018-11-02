package com.zhongxb.concurrent.chapter27;

import java.util.LinkedList;

/**
 * ActiveMessageQueue主要用于传送调用线程通过Proxy提交过来的MethodMessage，但是这个传送带允许存放无限的MethodMessage
 * @author Mr.zxb
 * @date 2018-11-01 16:12
 */
public class ActiveMessageQueue {

    /**
     * 用于存放提交的MethodMessage消息
     */
    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {
        // 启动Worker线程
        new ActiveDaemonThread(this).start();
    }

    public void offer(MethodMessage message) {
        synchronized (this) {
            messages.addLast(message);
            // 因为只有一个线程负责take数据，因此没有必要使用notifyAll();
            this.notify();
        }
    }

    protected MethodMessage take() {
        synchronized (this) {
            // 当MethodMessage队列中没有Message的时候，执行线程进入阻塞
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 获取其中一个MethodMessage并且从队列中移除
            return messages.removeFirst();
        }
    }
}
