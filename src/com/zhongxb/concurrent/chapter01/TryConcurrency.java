package com.zhongxb.concurrent.chapter01;

public class TryConcurrency {
    public static void main(String[] args) {
        new Thread(TryConcurrency::enjoyMusic).start();
        browseNews();
    }

    private static void browseNews() {
        for (;;) {
            System.out.println("看新闻");
        }
    }

    private static void enjoyMusic() {
        for (;;) {
            System.out.println("听音乐");
        }
    }
}
