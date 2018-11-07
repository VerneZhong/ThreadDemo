package com.zhongxb.concurrent.chapter29.example02;

/**
 * @author Mr.zxb
 * @date 2018-11-07 15:59
 */
public class EventDispatcherExample {

    /**
     * InputEvent中定义两个属性x和y，主要用于在其他Channel中的运算
     */
    static class InputEvent extends Event {
        private final int x;
        private final int y;

        public InputEvent(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    /**
     * 用于存放结果的Event
     */
    static class ResultEvent extends Event {
        private final int result;

        public ResultEvent(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

    /**
     * 处理ResultEvent的Handler（Channel），只是简单地将计算结果输出到控制台
     */
    static class ResultEventHandler implements Channel<ResultEvent> {

        @Override
        public void dispatch(ResultEvent message) {
            System.out.println("The result is:" + message.getResult());
        }
    }

    /**
     * 该类需要向Router发送Event，因此在构造的时候需要传入Dispatcher
     */
    static class InputEventHandler implements Channel<InputEvent> {

        private final EventDispatcher dispatcher;

        public InputEventHandler(EventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        /**
         * 将计算结果构造成新的Event提交给Router
         * @param message
         */
        @Override
        public void dispatch(InputEvent message) {
            System.out.printf("X:%d,Y:%d\n", message.getX(), message.getY());
            int result = message.getX() + message.getY();
            dispatcher.dispatch(new ResultEvent(result));
        }
    }

    public static void main(String[] args) {

        // 构造Router
        EventDispatcher dispatcher = new EventDispatcher();
        // 将Event和Handler（Channel）的绑定关系注册到Dispatcher
        dispatcher.registerChannel(InputEvent.class, new InputEventHandler(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new ResultEventHandler());
        dispatcher.dispatch(new InputEvent(1, 2));
        dispatcher.dispatch(new InputEvent(5, 5));
    }

}
