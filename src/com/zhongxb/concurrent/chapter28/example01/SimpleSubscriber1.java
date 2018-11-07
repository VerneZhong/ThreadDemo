package com.zhongxb.concurrent.chapter28.example01;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * test
 * @author Mr.zxb
 * @date 2018-11-07 14:17
 */
public class SimpleSubscriber1 {

    @Subscribe
    public void m1(String message) {
        System.out.println("========SimpleSubscriber1--m1=======" + message);
    }

    @Subscribe(topic = "test")
    public void m2(String message) {
        System.out.println("========SimpleSubscriber1--m2=======" + message);
    }

    public static void main(String[] args) {
        // 同步EventBus
        Bus bus = new EventBus("TestBus");
        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());
        bus.post("hello");
        System.out.println("-------------");
        bus.post("hello", "test");
    }
}

class SimpleSubscriber2 {

    @Subscribe
    public void m1(String message) {
        System.out.println("========SimpleSubscriber2--m1=======" + message);
    }

    @Subscribe(topic = "test")
    public void m2(String message) {
        System.out.println("========SimpleSubscriber2--m2=======" + message);
    }

    public static void main(String[] args) {
        // 异步EventBus
        Bus bus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());
        bus.post("hello");
        System.out.println("-------------");
        bus.post("hello", "test");
    }

}
