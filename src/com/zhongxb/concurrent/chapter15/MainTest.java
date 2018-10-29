package com.zhongxb.concurrent.chapter15;

public class MainTest {

    public static void main(String[] args) {

        ObserableThread<String> obserableThread = new ObserableThread<>(() -> {
            System.out.println("正在执行任务...");
            return "hello zxb.";
        });

        obserableThread.start();
    }
}
