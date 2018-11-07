package com.zhongxb.concurrent.chapter28.example01;

import java.lang.reflect.Method;

/**
 * Subscriber类封装了对象实例和被@Subscribe标记的方法，也就是说一个对象实例有可能会被封装若干个Subscriber
 * @author Mr.zxb
 * @date 2018-11-06 17:00
 */
public class Subscriber {

    private final Object subscribeObject;

    private final Method subscribeMethod;

    private boolean disable = false;

    public Subscriber(Object subscribeObject, Method subscribeMethod) {
        this.subscribeObject = subscribeObject;
        this.subscribeMethod = subscribeMethod;
    }

    public Object getSubscribeObject() {
        return subscribeObject;
    }

    public void setDisable(boolean b) {
        this.disable = b;
    }

    public Method getSubscribeMethod() {
        return subscribeMethod;
    }

    public boolean isDisable() {
        return disable;
    }
}
