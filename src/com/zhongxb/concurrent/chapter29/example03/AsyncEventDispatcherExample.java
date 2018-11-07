package com.zhongxb.concurrent.chapter29.example03;

import com.zhongxb.concurrent.chapter29.example02.Event;
import com.zhongxb.concurrent.chapter29.example02.EventDispatcherExample;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-11-07 16:58
 */
public class AsyncEventDispatcherExample {

    /**
     * 主要用于处理InputEvent，但是需要继承AsyncChannel
     */
    static class AsyncInputEventHandler extends AsyncChannel {

        private final AsyncEventDispatcher dispatcher;

        public AsyncInputEventHandler(AsyncEventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        /**
         * 不同于以同步的方式实现dispatch，异步的方式需要实现handle
         * @param message
         */
        @Override
        protected void handle(Event message) {
            EventDispatcherExample.InputEvent inputEvent = (EventDispatcherExample.InputEvent) message;
            System.out.printf("X:%d,Y:%d\n", inputEvent.getX(), inputEvent.getY());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = inputEvent.getX() + inputEvent.getY();
            dispatcher.dispatch(new EventDispatcherExample.ResultEvent(result));
        }
    }

    /**
     * 主要用于处理InputEvent，但是需要继承AsyncChannel
     */
    static class AsyncResultEventHandler extends AsyncChannel {

        @Override
        protected void handle(Event message) {
            EventDispatcherExample.ResultEvent resultEvent = (EventDispatcherExample.ResultEvent) message;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The result is:" + resultEvent.getResult());
        }
    }

    public static void main(String[] args) {

        // 定义AsyncEventDispatcher
        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

        // 注册Event和Channel之间的关系
        dispatcher.registerChannel(EventDispatcherExample.InputEvent.class, new AsyncInputEventHandler(dispatcher));
        dispatcher.registerChannel(EventDispatcherExample.ResultEvent.class, new AsyncResultEventHandler());

        // 提交需要处理的Message
        dispatcher.dispatch(new EventDispatcherExample.InputEvent(2, 2));

    }

}
