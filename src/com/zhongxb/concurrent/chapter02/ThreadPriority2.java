package com.zhongxb.concurrent.chapter02;

public class ThreadPriority2 {

    public static void main(String[] args) {

        // 定义一个线程组
        ThreadGroup group = new ThreadGroup("test");
        // 将线程组的优先级设置为7
        group.setMaxPriority(7);
        // 定义一个线程，加入到线程组
        Thread thread = new Thread(group, "test-thread");
        //
        thread.setPriority(10);
        System.out.println(thread.getPriority());

    }
}
