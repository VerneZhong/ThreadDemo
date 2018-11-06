package com.zhongxb.concurrent.chapter27.example01;

import com.zhongxb.concurrent.chapter27.example01.ActiveMessageQueue;
import com.zhongxb.concurrent.chapter27.example01.OrderService;
import com.zhongxb.concurrent.chapter27.example01.OrderServiceProxy;

/**
 * 订单工厂类
 * @author Mr.zxb
 * @date 2018-11-01 16:49
 */
public final class OrderServiceFactory {

    /**
     * 将ActiveMessageQueue定义成static的目的是，保持其在整个JVM进程中是唯一的，并且ActiveDaemonThread会在此刻启动
     */
    public static final ActiveMessageQueue queue = new ActiveMessageQueue();

    private OrderServiceFactory() {}

    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, queue);
    }
}
