package com.zhongxb.concurrent.chapter28.example01;

import java.lang.reflect.Method;

/**
 * 该接口提供了获取消息源、消息体，以及该消息是由哪一个订阅者的哪个Subscribe方法所接受，
 * 主要用于消息推送出错时被回调接口
 * @author Mr.zxb
 * @date 2018-11-07 09:42
 */
public interface EventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();
}
