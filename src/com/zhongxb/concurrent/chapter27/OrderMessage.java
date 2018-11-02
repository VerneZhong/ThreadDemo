package com.zhongxb.concurrent.chapter27;

import java.util.Map;

/**
 * @author Mr.zxb
 * @date 2018-11-01 16:23
 */
public class OrderMessage extends MethodMessage {

    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        // 获取参数
        String account = (String) params.get("account");
        long orderId = (long) params.get("orderId");

        // 执行真正的order方法
        orderService.order(account, orderId);
    }
}
