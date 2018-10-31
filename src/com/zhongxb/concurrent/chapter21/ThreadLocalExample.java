package com.zhongxb.concurrent.chapter21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ThreadLocal案例
 * @author Mr.zxb
 * @date 2018-10-30 15:51
 */
public class ThreadLocalExample {

    public static void main(String[] args) {

        // 创建ThreadLocal实例
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        // 创建10个线程，使用ThreadLocal
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                threadLocal.set(i);
                System.out.println(Thread.currentThread() + " set i " + threadLocal.get());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread() + " set i " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

        ThreadLocal<Object> objectThreadLocal = ThreadLocal.withInitial(Object::new);
        System.out.println(objectThreadLocal.get());
    }
}
