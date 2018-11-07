package com.zhongxb.concurrent.chapter29.example02;

/**
 * Event 是对Message的一个最简单的实现，在以后的使用中，将Event直接作为其他Message的基类即可
 * @author Mr.zxb
 * @date 2018-11-07 15:46
 */
public class Event implements Message {
    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
