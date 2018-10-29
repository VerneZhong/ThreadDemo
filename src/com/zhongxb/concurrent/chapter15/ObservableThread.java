package com.zhongxb.concurrent.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * 监控任务的生命周期
 * @author zxb
 * @param <T>
 */
public class ObservableThread<T> implements Observable {

    private final Task<T> task;

    private Cycle cycle;

    private final TaskLifeCyclc taskLifeCyclc;

    public ObservableThread(Task<T> task) {
        this(new TaskLifeCyclc.EmptyLifeCycle(), task);
    }

    public ObservableThread(TaskLifeCyclc taskLifeCyclc, Task<T> task) {
        if (task == null) {
            throw new IllegalArgumentException("The task is required.");
        }
        this.taskLifeCyclc = taskLifeCyclc;
        this.task = task;
    }

    @Override
    public final void run() {
        // 返回各个生命周期状态
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            TimeUnit.SECONDS.sleep(5);
            this.update(Cycle.DONE, task.call(), null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    @Override
    public void start() {
        this.run();
    }

    @Override
    public void interrupt() {

    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (taskLifeCyclc == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.taskLifeCyclc.onStart(Thread.currentThread());
                    break;
                case RUNNING:
                    this.taskLifeCyclc.onRunning(Thread.currentThread());
                    break;
                case DONE:
                    this.taskLifeCyclc.onFinish(Thread.currentThread(), result);
                    break;
                case ERROR:
                    this.taskLifeCyclc.onError(Thread.currentThread(), e);
                    break;
                default:
                    break;
            }
        } catch (Exception e1) {
            this.cycle = Cycle.ERROR;
            throw e1;
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
