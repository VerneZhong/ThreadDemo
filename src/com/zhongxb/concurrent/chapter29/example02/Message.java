package com.zhongxb.concurrent.chapter29.example02;

/**
 * 在基于Message的系统中，每一个Event也可以被称为Message，Message是对于Event更高一个层级的抽象，
 * 每一个Message都有一个特定的Type用于对应的Handler做关联
 * @author Mr.zxb
 * @date 2018-11-07 15:36
 */
public interface Message {

    /**
     * 返回Message的类型
     * @return
     */
    Class<? extends Message> getType();
}
