package com.zhongxb.concurrent.chapter28.example01;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 注册表
 * @author Mr.zxb
 * @date 2018-11-06 16:17
 */
class Registry {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscribeContainer = new ConcurrentHashMap<>();
    
    public void bind(Object subscriber) {
        // 获取Subscriber Object的方法集合然后进行绑定
        List<Method> methods = getSubscribeMethods(subscriber);
        methods.forEach(m -> tierSubscriber(subscriber, m));
    }

    /**
     * 通过topic获取订阅者列表
     * @param topic
     * @return
     */
    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(final String topic) {
        return subscribeContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method m) {
        final Subscribe subscribe = m.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        // 当某topic没有Subscriber Queue的时候创建一个
        subscribeContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());

        // 创建一个Subscriber并且加入Subscriber列表中
        subscribeContainer.get(topic).add(new Subscriber(subscriber, m));
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();

        // 不断获取当前类和父类的所有@Subscribe方法
        while (temp != null) {
            // 获取所有方法
            Method[] declareMethods = temp.getDeclaredMethods();
            // 只有public方法 && 有一个入参 && 最重要的是被 @Subscribe标记的方法才符合回调方法
            Arrays.stream(declareMethods).filter(m -> m.isAnnotationPresent(Subscribe.class)
                        && m.getParameterCount() == 1
                        && m.getModifiers() == Modifier.PUBLIC).forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;

    }

    public void unbind(Object subscriber) {
        // unbind为了提高速度，只对Subscriber进行失效操作
        subscribeContainer.forEach((key, queue) -> {
            queue.forEach(s -> {
                if (s.getSubscribeObject() == subscriber) {
                    s.setDisable(true);
                }
            });
        });
    }
}
