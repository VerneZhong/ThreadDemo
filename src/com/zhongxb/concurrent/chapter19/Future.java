package com.zhongxb.concurrent.chapter19;

/**
 * Future提供获取计算结果和判断任务是否完成的两个接口，
 * 其中获取结果接口将会导致调用阻塞
 * @author Mr.zxb
 * @date 2018-10-30 11:29
 */
public interface Future<T> {

    /**
     * 返回计算后的结果，该方法会导致陷入阻塞
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;

    /**
     * 判断任务是否已经执行完成
     * @return
     */
    boolean done();
}
