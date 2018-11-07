package com.zhongxb.concurrent.chapter29.example01;

import java.util.LinkedList;

/**
 * @author Mr.zxb
 * @date 2018-11-07 15:29
 */
public class FooEventDrivenExample {

    /**
     * 用于处理A类型的Event
     *
     * @param event
     */
    public static void handleEventA(Event event) {
        System.out.println(event.getData().toLowerCase());
    }

    /**
     * 用于处理B类型的Event
     *
     * @param event
     */
    public static void handleEventB(Event event) {
        System.out.println(event.getData().toUpperCase());
    }

    public static void main(String[] args) {

        LinkedList<Event> events = new LinkedList<>();

        events.add(new Event("A", "hello"));
        events.add(new Event("A", "I am Event A"));
        events.add(new Event("B", "I am Event B"));
        events.add(new Event("B", "world"));

        Event e;
        while (!events.isEmpty()) {
            // 从队列中不断移除，根据不同类型
            e = events.remove();
            switch (e.getType()) {
                case "A":
                    handleEventA(e);
                    break;
                case "B":
                    handleEventB(e);
                    break;
                default:
                    break;
            }
        }
    }

}
