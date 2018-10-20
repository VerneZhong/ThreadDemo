package com.zhongxb.concurrent.chapter02;

public class ThreadPriority3 {

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println(t.getId()+" " +t.getPriority());
        Thread t1 = new Thread();
        System.out.println(t1.getId()+" "+ t1.getPriority());

        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println(t3.getId()+" "+t3.getPriority());
        });
        t2.setPriority(7);
        t2.start();
        System.out.println(t2.getId()+" "+t2.getPriority());

    }
}
