package com.zhongxb.concurrent.chapter08;

/**
 * 当队列中的线程数量达到了limit上限时，决定采用何种策略通知提交者。
 * 提供默认三种实现
 * @author zxb
 */
@FunctionalInterface
public interface DenyPolicy {

    /**
     * 拒绝方法
     * @param runnable
     * @param threadPool
     */
    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * 该拒绝策略直接会将任务丢弃
     */
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }

    /**
     * 该拒绝策略会向任务提交者抛出异常
     */
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("The runnable " + runnable + " will be abort.");
        }
    }

    /**
     * 该拒绝策略会使任务在提交者所在的线程中执行任务
     */
    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
