package com.zhongxb.concurrent.chapter21;

/**
 * ThreadLocal设计线程上下文
 * @author Mr.zxb
 * @date 2018-10-31 11:01
 */
public class ActionContext {

    /**
     * 每一个线程都有独立的Context实例
     */
    static class Context {

        // 成员变量
    }

    /**
     * 实例化ThreadLocal并初始化
     */
    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);

    public static Context getContext() {
        return context.get();
    }
}
