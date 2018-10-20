package com.zhongxb.concurrent.chapter01;

public class TicketWindow extends Thread {

    private final String name;
    public static final int MAX_TICKET = 5000;
    private volatile static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (index <= MAX_TICKET) {
                System.out.println("柜台：" + this.name + "  当前号码是：" + index);
                index++;
            }
        }
    }

    public static void main(String[] args) {

        TicketWindow t1 = new TicketWindow("一号柜机");
        t1.start();

        TicketWindow t2 = new TicketWindow("二号柜机");
        t2.start();

        TicketWindow t3 = new TicketWindow("三号柜机");
        t3.start();

        TicketWindow t4 = new TicketWindow("四号柜机");
        t4.start();

    }
}
