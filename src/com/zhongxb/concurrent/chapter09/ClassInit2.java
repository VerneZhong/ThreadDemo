package com.zhongxb.concurrent.chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClassInit2 {

    static {
        System.out.println("The ClassInit static code block will be invoke.");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        IntStream.range(0, 5).mapToObj(i -> new Thread(ClassInit2::new)).forEach(Thread::start);
    }
}
