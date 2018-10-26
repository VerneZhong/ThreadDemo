package com.zhongxb.concurrent.chapter10;

import java.util.ArrayList;
import java.util.List;

public class SimpleClass {

    // 在simpleClass使用byte[]
    private static byte[] buffer = new byte[8];

    // 在simpleClass使用String
    private static String str = "";

    // 在simpleClass使用list
    private static List<String> list = new ArrayList<>();

    static {
        buffer[0] = 1;
        str = "simple";
        list.add("element");
        System.out.println(buffer[0]);
        System.out.println(str);
        System.out.println(list.get(0));
    }
}
