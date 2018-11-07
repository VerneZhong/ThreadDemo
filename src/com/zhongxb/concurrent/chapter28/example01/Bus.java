package com.zhongxb.concurrent.chapter28.example01;

/**
 * 定义了EventBus的所有使用方法
 * @author Mr.zxb
 * @date 2018-11-06 16:04
 */
public interface Bus {

    /**
     * 将某个对象注册到Bus上，从此之后该类就成为Subscriber了
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 将某个对象从Bus上取消注册，取消注册就不会再收到订阅消息了
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * 提交Event到默认的topic
     * @param event
     */
    void post(Object event);

    /**
     * 提交Event到指定的topic
     * @param event
     * @param topic
     */
    void post(Object event, String topic);

    /**
     * 关闭该bus
     */
    void close();

    /**
     * 返回Bus的名称标识
     * @return
     */
    String getBusName();
}
