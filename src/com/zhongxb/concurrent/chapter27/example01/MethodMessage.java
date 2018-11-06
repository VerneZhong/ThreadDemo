package com.zhongxb.concurrent.chapter27.example01;

import com.zhongxb.concurrent.chapter27.example01.OrderService;

import java.util.Map;

/**
 * 主要用于收集每一个接口的方法参数，并且提供execute方法供ActiveDaemonThread直接调用
 * @author Mr.zxb
 * @date 2018-11-01 16:17
 */
public abstract class MethodMessage {

    /**
     * 用于收集方法参数，如果又返回Future类型则一并收集
     */
    protected final Map<String, Object> params;

    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    /**
     * 抽象方法
     */
    public abstract void execute();
}
