package com.zhongxb.concurrent.chapter09;

public class Child extends Simple {

    static {
        System.out.println("I's child will be init.");
    }

    public static int Y = 0;
}
