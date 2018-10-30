package com.zhongxb.concurrent.chapter18;

import java.util.Arrays;
import java.util.List;

/**
 * 不可变对象设计
 * ArrayList生成的stream是线程安全的，同样具备不可变性
 * @author Mr.zxb
 * @date 2018-10-30 10:50
 */
public class ArrayListStream {

    public static void main(String[] args) {

        // 定义一个ArrayList，用Arrays初始化
        List<String> list = Arrays.asList("Java", "C#", "Scala", "Go", "Python", "PHP", "Kotlin", "Swift");

        // 获取并行的stream，然后对数据进行加工，然后输出
        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("------------");
        list.forEach(System.out::println);
    }
}
