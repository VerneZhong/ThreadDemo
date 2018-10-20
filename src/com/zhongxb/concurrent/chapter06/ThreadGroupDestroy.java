package com.zhongxb.concurrent.chapter06;

/**
 * 线程组销毁,在父group中删除自己
 */
public class ThreadGroupDestroy {

    public static void main(String[] args) {

        ThreadGroup group = new ThreadGroup("TestGroup");

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("group.isDestroy=" + group.isDestroyed());
        mainGroup.list();

        // 销毁线程组
        group.destroy();

        System.out.println("group.isDestroy=" + group.isDestroyed());
        mainGroup.list();

    }
}
