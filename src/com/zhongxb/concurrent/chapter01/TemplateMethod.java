package com.zhongxb.concurrent.chapter01;

/**
 * 模板方法
 */
public abstract class TemplateMethod {

    public final void print(String msg) {
        System.out.println("***************");
        wrapPrint(msg);
        System.out.println("***************");
    }

    protected abstract void wrapPrint(String msg);

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String msg) {
                System.out.println(msg + "--t1");
            }
        };
        t1.print("hello");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String msg) {
                System.out.println(msg + "--t2");
            }
        };
        t2.print("hello");
    }
}
