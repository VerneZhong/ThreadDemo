package com.zhongxb.concurrent.chapter27.example02;

import com.zhongxb.concurrent.chapter19.Future;
import com.zhongxb.concurrent.chapter27.example01.OrderService;
import com.zhongxb.concurrent.chapter27.example01.OrderServiceImpl;

/**
 * @author Mr.zxb
 * @date 2018-11-06 11:04
 */
public class Test2 {

    public static void main(String[] args) throws InterruptedException {

        OrderService orderService = ActiveServiceFactory.active(new OrderServiceImpl());
        Future<String> future = orderService.findOrderDetails(123123);
        System.out.println("I will be returned immediately");
        System.out.println(future.get());
    }
}
