package com.zhongxb.concurrent.chapter27.example01;

import com.zhongxb.concurrent.chapter19.Future;

import java.util.HashMap;
import java.util.Map;

/**
 * OrderServiceProxy是OrderService的子类，其作用是将每一个方法都封装成MethodMessage，
 * 然后提交给ActiveMessage队列，
 * @author Mr.zxb
 * @date 2018-11-01 16:11
 */
public class OrderServiceProxy implements OrderService {

    private final OrderService orderService;

    private final ActiveMessageQueue activeMessageQueue;

    public OrderServiceProxy(OrderService orderService, ActiveMessageQueue activeMessageQueue) {
        this.orderService = orderService;
        this.activeMessageQueue = activeMessageQueue;
    }

    @Override
    public Future<String> findOrderDetails(long orderId) {
        // 定义一个ActiveFuture，并且可支持立即返回
        final ActiveFuture<String> activeFuture = new ActiveFuture<>();
        // 收集方法入参以及返回的ActiveFuture封装成MethodMessage
        Map<String, Object> params = new HashMap<>(16);
        params.put("orderId", orderId);
        params.put("activeFuture", activeFuture);

        MethodMessage message = new FindOrderDetailsMessage(params, orderService);

        // 将MethodMessage保存至activeMessageQueue中
        activeMessageQueue.offer(message);

        return activeFuture;
    }

    @Override
    public void order(String account, long orderId) {
        // 收集方法参数，并且封装成MethodMessage，然后offer至队列中
        Map<String, Object> params = new HashMap<>(16);
        params.put("account", account);
        params.put("orderId", orderId);

        MethodMessage methodMessage = new OrderMessage(params, orderService);
        activeMessageQueue.offer(methodMessage);
    }
}
