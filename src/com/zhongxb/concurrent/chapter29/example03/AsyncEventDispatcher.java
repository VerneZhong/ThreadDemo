package com.zhongxb.concurrent.chapter29.example03;

import com.zhongxb.concurrent.chapter29.example02.Channel;
import com.zhongxb.concurrent.chapter29.example02.DynamicRouter;
import com.zhongxb.concurrent.chapter29.example02.Event;
import com.zhongxb.concurrent.chapter29.example02.MessageMatcherException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Mr.zxb
 * @date 2018-11-07 16:43
 */
public class AsyncEventDispatcher implements DynamicRouter<Event> {

    /**
     * 使用线程安全的ConcurrentHashMap替换HashMap
     */
    private final Map<Class<? extends Event>, AsyncChannel> routerTable;

    public AsyncEventDispatcher() {
        this.routerTable = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
        // 在AsyncEventDispatcher中，Channel必须是AsyncChannel类型
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalArgumentException("The channel must be AsyncChannel Type.");
        }
        this.routerTable.putIfAbsent(messageType, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("Can't match the channel for [" + message.getType() + "] type");
        }
    }

    /**
     * 关闭所有的Channel以释放资源
     */
    public void shutdown() {
        routerTable.values().forEach(AsyncChannel::stop);
    }
}
