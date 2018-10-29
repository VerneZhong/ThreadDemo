package com.zhongxb.concurrent.chapter16;

/**
 * test EatNoodleThread
 * @author Mr.zxb
 * @date 2018-10-29 11:15
 */
public class EatNoodleThreadTest {

    public static void main(String[] args) {

        Tableware fork = new Tableware("叉子");
        Tableware knife = new Tableware("刀子");

        TablewarePair tablewarePair = new TablewarePair(fork, knife);

//        EatNoodleThread eatNoodleThread1 = new EatNoodleThread("张三", knife, fork);
//        EatNoodleThread eatNoodleThread2 = new EatNoodleThread("李四", fork, knife);
        EatNoodleThread eatNoodleThread1 = new EatNoodleThread("张三", tablewarePair);
        EatNoodleThread eatNoodleThread2 = new EatNoodleThread("李四", tablewarePair);
        eatNoodleThread1.start();
        eatNoodleThread2.start();

    }
}
