package com.zhongxb.concurrent.chapter04;

public class TicketWindowRunnable implements Runnable {

    public static final int MAX_TICKET = 50000;
    private int index = 1;

    public static final Object KEY = new Object();

    @Override
    public void run() {
        synchronized (KEY) {
            while (index <= MAX_TICKET) {
                System.out.println(Thread.currentThread() + " 的号码是："+(index++));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TicketWindowRunnable task = new TicketWindowRunnable();
        Thread t1 = new Thread(task, "一号柜机");
        Thread t2 = new Thread(task, "二号柜机");
        Thread t3 = new Thread(task, "三号柜机");
        Thread t4 = new Thread(task, "四号柜机");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
