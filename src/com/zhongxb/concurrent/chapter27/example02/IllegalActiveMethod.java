package com.zhongxb.concurrent.chapter27.example02;

/**
 * 方法不符合则被转换为Active方法时会抛出该异常
 * @author Mr.zxb
 * @date 2018-11-02 17:25
 */
public class IllegalActiveMethod extends Exception {

    public IllegalActiveMethod(String message) {
        super(message);
    }
}
