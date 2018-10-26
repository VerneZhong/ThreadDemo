package com.zhongxb.concurrent.chapter09;

import java.util.Random;

public class GlobalConstants {

    static {
        System.out.println("The GlobalConstants will be initialized.");
    }

    public static final int MAX = 100;

    public static final int RANDOM = new Random().nextInt();

    public static void main(String[] args) {

        System.out.println(GlobalConstants.MAX);
    }
}
