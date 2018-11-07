package com.zhongxb.concurrent.chapter29.example02;

/**
 * Channel主要用于接受来自Event Loop分配的消息，每一个Channel负责处理一种类型的消息
 * @author Mr.zxb
 * @date 2018-11-07 15:39
 */
public interface Channel<E extends Message> {

    /**
     * dispatch用于负责Message的调度
     * @param message
     */
    void dispatch(E message);
}
