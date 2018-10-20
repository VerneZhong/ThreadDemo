package com.zhongxb.concurrent.chapter01;

/**
 * 线程组
 */
public class ThreadGroupDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread("t1");

        ThreadGroup group = new ThreadGroup("testGroup");

        Thread t2 = new Thread(group, "t2");

        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        System.out.println("Main thread belong group:" + mainThreadGroup.getName());

        System.out.println("t1 and main thread belong the same group:"  + (mainThreadGroup == t1.getThreadGroup()));

        System.out.println("t2 and main thread belong the same group:"  + (mainThreadGroup == t2.getThreadGroup()));

        System.out.println("t2 thread group belong main TestGroup:"  + (group == t2.getThreadGroup()));

    }
}
