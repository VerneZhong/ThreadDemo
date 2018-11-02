package com.zhongxb.concurrent.chapter27;

import com.zhongxb.concurrent.chapter19.Future;

/**
 * 订单接口
 * @author Mr.zxb
 * @date 2018-11-01 16:00
 */
public interface OrderService {

    /**
     * 根据订单编号查询订单明细，有入参也有返回值，但是返回类型必须是Future
     * @param orderId
     * @return
     */
    Future<String> findOrderDetails(long orderId);

    /**
     * 提交订单，没有返回值
     * @param account
     * @param orderId
     */
    void order(String account, long orderId);
}
