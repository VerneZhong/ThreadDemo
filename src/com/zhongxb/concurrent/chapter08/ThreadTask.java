package com.zhongxb.concurrent.chapter08;

/**
 * ThreadTask是InternalTask和Thread的一个组合
 * @author zxb
 */
public class ThreadTask {

    private Thread thread;
    private InternalTask internalTask;

    public ThreadTask(Thread thread, InternalTask internalTask) {
        this.thread = thread;
        this.internalTask = internalTask;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public InternalTask getInternalTask() {
        return internalTask;
    }

    public void setInternalTask(InternalTask internalTask) {
        this.internalTask = internalTask;
    }
}
