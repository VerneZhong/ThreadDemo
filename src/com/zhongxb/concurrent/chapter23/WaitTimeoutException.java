package com.zhongxb.concurrent.chapter23;

/**
 * @author Mr.zxb
 * @date 2018-10-31 15:06
 */
public class WaitTimeoutException extends Exception {

    public WaitTimeoutException(String message) {
        super(message);
    }
}
