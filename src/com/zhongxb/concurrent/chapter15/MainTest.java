package com.zhongxb.concurrent.chapter15;

public class MainTest {

    public static void main(String[] args) {

        ObservableThread<String> observableThread = new ObservableThread<>(() -> {
            System.out.println("正在执行任务...");
            return "hello zxb.";
        });

        observableThread.start();
    }
}
