package com.zhongxb.concurrent.chapter01;

/**
 * stackSize越小创建的线程越多，越大则代表着正在线程内方法调用递归的深度越深
 */
public class ThreadConstruction {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter the stack size.");
//            System.exit(1);
        }

        ThreadGroup group = new ThreadGroup("TestGroup");

        Runnable runnable = new Runnable() {
            final int MAX = Integer.MAX_VALUE;

            @Override
            public void run() {
                int i = 0;
                recurce(i);
            }

            private void recurce(int i) {
                System.out.println(i);
                if (i < MAX) {
                    recurce(i + 1);
                }
            }
        };

        Thread thread = new Thread(group, runnable, "Test", Integer.parseInt("1"));
        thread.start();
    }
}
