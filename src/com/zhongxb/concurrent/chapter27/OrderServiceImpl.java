package com.zhongxb.concurrent.chapter27;

import com.zhongxb.concurrent.chapter19.Future;
import com.zhongxb.concurrent.chapter19.FutureService;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-11-01 16:03
 */
public class OrderServiceImpl implements OrderService {

    @ActiveMethod
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            try {
                // 通过休眠来模拟该方法的执行毕竟耗时
                TimeUnit.SECONDS.sleep(10);
                System.out.println("process the orderID-" + orderId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The order Details Information.";
        }, orderId);
    }

    @ActiveMethod
    @Override
    public void order(String account, long orderId) {
        try {
            // 通过休眠来模拟该方法的执行毕竟耗时
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account " + account + ", orderId " + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
