package com.zhongxb.concurrent.chapter29.example02;

/**
 * 在EventDispatcher中有一个注册表routerTable，主要用于存放不同类型Message对应的Channel，如果没有与Message
 * 相对应的Channel，则会抛出无法匹配的异常
 * @author Mr.zxb
 * @date 2018-11-07 15:55
 */
public class MessageMatcherException extends RuntimeException {

    public MessageMatcherException(String message) {
        super(message);
    }
}
