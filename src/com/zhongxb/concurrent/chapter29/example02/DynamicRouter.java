package com.zhongxb.concurrent.chapter29.example02;

/**
 * Router的作用类似于Event Loop，其主要是帮助Event找到合适的Channel并且传送给它
 * @author Mr.zxb
 * @date 2018-11-07 15:42
 */
public interface DynamicRouter<E extends Message> {

    /**
     * 针对每一种Message类型注册相关的Channel，只有找到合适的Channel该Message才会被处理
     * @param messageType
     * @param channel
     */
    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    /**
     * 为响应的Channel分配Message
     * @param message
     */
    void dispatch(E message);
}
