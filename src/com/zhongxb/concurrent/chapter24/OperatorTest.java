package com.zhongxb.concurrent.chapter24;

/**
 * @author Mr.zxb
 * @date 2018-10-31 17:46
 */
public class OperatorTest {

    public static void main(String[] args) {

        Operator operator = new Operator();

        for (int i = 1; i <= 5; i++) {
            operator.call("新的任务" + i + "来了");
        }
    }
}
