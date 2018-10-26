package com.zhongxb.concurrent.chapter09;

/**
 * 导致类的初始化：
 *  1.通过new关键字
 *  2.访问类的静态变量
 *  3.访问类的静态方法
 *  4.对某个类进行反射操作
 *  5.初始化子类会导致父类的初始化（初始化子类，会先初始化父类）
 *  6.访问子类的静态变量会导致父类和子类都初始化（注意：通过子类访问父类的静态变量，只会导致父类初始化），静态常量除外，访问子类的静态常量
 *      会初始化父类，不会初始化子类需要注意
 *
 *
 */
public class Simple {

    static {
        System.out.println("I's simple will be init.");
    }

    public static int I = 1;

    public static void test() {
    }

    public static void main(String[] args) {
//        new Simple();
//        System.out.println(Simple.I);
//        Simple.test();
//        new Child();
//        System.out.println(Child.Y);

        Simple[] simples = new Simple[4];
        System.out.println(simples.length);
    }
}
