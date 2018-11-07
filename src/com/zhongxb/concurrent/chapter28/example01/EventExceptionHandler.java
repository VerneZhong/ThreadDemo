package com.zhongxb.concurrent.chapter28.example01;

/**
 * EventBus 会将方法的调用交给Runnable接口去执行，我们知道Runnable接口不能抛出checked异常信息，并且在每一个subscribe方法中，
 * 也不允许将异常抛出从而影响EventBus对后续Subscriber进行消息推送，但异常信息又不能忽略掉，因此注册一个异常回调接口就可以知道
 * 在进行消息广播时都发生了什么
 * @author Mr.zxb
 * @date 2018-11-06 16:22
 */
public interface EventExceptionHandler {

    /**
     * 异常处理逻辑
     * @param cause
     * @param context
     */
    void handle(Throwable cause, EventContext context);
}
