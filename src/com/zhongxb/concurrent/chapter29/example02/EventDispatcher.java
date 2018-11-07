package com.zhongxb.concurrent.chapter29.example02;

import java.util.HashMap;
import java.util.Map;

/**
 * EventDispatcher是对DynamicRouter的一个最基本实现，适合在单线程的情况下进行使用，因此不需要考虑线程安全的问题
 * @author Mr.zxb
 * @date 2018-11-07 15:48
 */
public class EventDispatcher implements DynamicRouter<Message> {

    /**
     * 用于保存Channel和Message之间的关系
     */
    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {
        // 初始化RouterTable,但是在该实现中，使用HashMap作为路由表
        this.routerTable = new HashMap<>(16);
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.putIfAbsent(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (routerTable.containsKey(message.getType())) {
            // 直接获取对应的Channel处理Message
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("Can't match the channel for [" + message.getType() + "] type");
        }
    }
}
