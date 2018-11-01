package com.zhongxb.concurrent.chapter24.example01;

/**
 * @author Mr.zxb
 * @date 2018-10-31 17:34
 */
public class Request {

    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
