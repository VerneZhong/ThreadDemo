package com.zhongxb.concurrent.chapter28.example01;

/**
 * 普通对象
 * @author Mr.zxb
 * @date 2018-11-06 17:18
 */
public class SimpleObject {

    @Subscribe(topic = "alex-topic")
    public void test2(Integer x) {

    }

    @Subscribe(topic = "test-topic")
    public void test3(Integer x) {

    }
}
