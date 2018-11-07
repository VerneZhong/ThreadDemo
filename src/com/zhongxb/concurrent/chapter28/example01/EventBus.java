package com.zhongxb.concurrent.chapter28.example01;

import java.util.concurrent.Executor;

/**
 * 同步的方式，推送Event
 *
 * @author Mr.zxb
 * @date 2018-11-06 16:14
 */
public class EventBus implements Bus {

    /**
     * 用于维护Subscriber的注册表
     */
    private final Registry registry = new Registry();

    /**
     * Event Bus的名字
     */
    private String busName;

    /**
     * 默认的Event Bus的名字
     */
    private static final String DEFAULT_BUS_NAME = "default";

    /**
     * 默认的topic的名字
     */
    public static final String DEFAULT_TOPIC = "default-topic";

    private final Dispatcher dispatcher;

    public EventBus() {
        this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(String busName) {
        this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(String busName, EventExceptionHandler exceptionHandler, Executor executor) {
        this.busName = busName;
        this.dispatcher = Dispatcher.newDispatcher(exceptionHandler, executor);
    }

    public EventBus(EventExceptionHandler exceptionHandler) {
        this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    /**
     * 将注册Subscriber的动作直接委托给Registry
     * @param subscriber
     */
    @Override
    public void register(Object subscriber) {
        this.registry.bind(subscriber);
    }

    /**
     * 解除注册同样委托给Registry
     * @param subscriber
     */
    @Override
    public void unregister(Object subscriber) {
        this.registry.unbind(subscriber);
    }

    /**
     * 提交Event到默认的topic
     * @param event
     */
    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }

    /**
     * 提交Event到指定的topic，具体动作是由Dispatcher来完成
     * @param event
     * @param topic
     */
    @Override
    public void post(Object event, String topic) {
        this.dispatcher.dispatch(this, registry, event, topic);
    }

    /**
     * 关闭该bus
     */
    @Override
    public void close() {
        this.dispatcher.close();
    }

    /**
     * 返回Bus的名称标识
     * @return
     */
    @Override
    public String getBusName() {
        return this.busName;
    }
}
