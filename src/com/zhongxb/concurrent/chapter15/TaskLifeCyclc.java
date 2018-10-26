package com.zhongxb.concurrent.chapter15;

public interface TaskLifeCyclc<T> {

    /**
     * 任务启动时触发onStart方法
     * @param thread
     */
    void onStart(Thread thread);

    /**
     * 任务正在运行时会触发onRunning方法
     * @param thread
     */
    void onRunning(Thread thread);

    /**
     * 任务运行结束时会触发onFinish方法，并返回任务执行结果result
     * @param thread
     * @param result
     */
    void onFinish(Thread thread, T result);

    /**
     * 任务执行报错时触发onError方法
     * @param thread
     * @param e
     */
    void onError(Thread thread, Exception e);

    /**
     * 生命周期接口的空实现（Adapter）
     * @param <T>
     */
    class EmptyLifeCycle<T> implements TaskLifeCyclc<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
