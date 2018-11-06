package com.zhongxb.concurrent.chapter27.example01;

import com.zhongxb.concurrent.chapter19.Future;
import com.zhongxb.concurrent.chapter27.example01.ActiveFuture;
import com.zhongxb.concurrent.chapter27.example01.MethodMessage;
import com.zhongxb.concurrent.chapter27.example01.OrderService;

import java.util.Map;

/**
 * @author Mr.zxb
 * @date 2018-11-01 16:17
 */
public class FindOrderDetailsMessage extends MethodMessage {
    public FindOrderDetailsMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        // 执行orderService的findOrderDetails方法
        Future<String> realFuture = orderService.findOrderDetails((Long) params.get("orderId"));
        ActiveFuture<String> activeFuture = (ActiveFuture<String>) params.get("activeFuture");

        try {
            // 调用orderServiceImpl返回的Future.get()，此方法会导致阻塞直到findOrderDetails方法完全执行结束
            String result = realFuture.get();
            // 当findOrderDetails执行结束时，将结果通过finish的方法传递给ActiveFuture
            activeFuture.finish(result);
        } catch (InterruptedException e) {
            activeFuture.finish(null);
        }
    }
}
