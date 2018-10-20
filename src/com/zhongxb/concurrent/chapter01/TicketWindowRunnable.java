package com.zhongxb.concurrent.chapter01;

public class TicketWindowRunnable implements Runnable {

    public static final int MAX_TICKET = 50;
    private static int index = 1;

    @Override
    public void run() {
        while (index <= MAX_TICKET) {
            System.out.println(Thread.currentThread() + " 的号码是："+(index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        TicketWindowRunnable task = new TicketWindowRunnable();
        Thread t1 = new Thread(task, "一号柜机");
        t1.start();

        Thread t2 = new Thread(task, "二号柜机");
        t2.start();

        Thread t3 = new Thread(task, "三号柜机");
        t3.start();

        Thread t4 = new Thread(task, "四号柜机");
        t4.start();

    }
}
