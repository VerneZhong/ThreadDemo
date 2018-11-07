package com.zhongxb.concurrent.chapter28.example01;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * 该类主要作用是将EventBus post的event推送给每一个注册到topic上的订阅者，
 * 具体的推送其实就是执行被@Subscribe注解的方法
 * @author Mr.zxb
 * @date 2018-11-06 16:20
 */
public class Dispatcher {

    private final Executor executorService;

    private final EventExceptionHandler eventExceptionHandler;

    public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;

    public static final Executor PRE_THREAD_EXECUTOR_SERVICE = PreThreadExecutorService.INSTANCE;

    private Dispatcher(Executor executorService, EventExceptionHandler eventExceptionHandler) {
        this.executorService = executorService;
        this.eventExceptionHandler = eventExceptionHandler;
    }

    /**
     * 创建实例
     * @param exceptionHandler
     * @param executor
     * @return
     */
    public static Dispatcher newDispatcher(EventExceptionHandler exceptionHandler, Executor executor) {
        return new Dispatcher(executor, exceptionHandler);
    }

    /**
     * 顺序执行的ExecutorService
     * @param eventExceptionHandler
     * @return
     */
    public static Dispatcher seqDispatcher(EventExceptionHandler eventExceptionHandler) {
        return new Dispatcher(SEQ_EXECUTOR_SERVICE, eventExceptionHandler);
    }

    /**
     * 线程负责一次消息推送
     * @param eventExceptionHandler
     * @return
     */
    public static Dispatcher perThreadDispatcher(EventExceptionHandler eventExceptionHandler) {
        return new Dispatcher(PRE_THREAD_EXECUTOR_SERVICE, eventExceptionHandler);
    }

    public void dispatch(Bus eventBus, Registry registry, Object event, String topic) {
        // 根据topic获取所有的Subscriber列表
        ConcurrentLinkedQueue<Subscriber> subscribers = registry.scanSubscriber(topic);
        if (null == subscribers) {
            if (eventExceptionHandler != null) {
                eventExceptionHandler.handle(new IllegalArgumentException("The topic " + topic + " not bind yet"), new BaseEventContext(eventBus.getBusName(), null, event));
            }
            return;
        }

        // 遍历所有的方法，并且通过反射的方式进行方法调用
        subscribers.stream().filter(subscriber -> !subscriber.isDisable()).filter(subscriber -> {
           Method method = subscriber.getSubscribeMethod();
            Class<?> aClass = method.getParameterTypes()[0];
            return aClass.isAssignableFrom(event.getClass());
        }).forEach(subscriber -> realInvokeSubscribe(subscriber, event, eventBus));
    }

    /**
     * 执行订阅者的方法
     * @param subscriber
     * @param event
     * @param bus
     */
    private void realInvokeSubscribe(Subscriber subscriber, Object event, Bus bus) {
        Method method = subscriber.getSubscribeMethod();
        Object object = subscriber.getSubscribeObject();
        executorService.execute(() -> {
            try {
                method.invoke(object, event);
            } catch (Exception e) {
                if (null != eventExceptionHandler) {
                    eventExceptionHandler.handle(e, new BaseEventContext(bus.getBusName(), subscriber, event));
                }
            }
        });
    }

    /**
     * 关闭该bus
     */
    public void close() {
        if (executorService instanceof ExecutorService) {
            ((ExecutorService) executorService).shutdown();
        }
    }

    /**
     * 顺序执行的ExecutorService
     */
    private static class SeqExecutorService implements Executor {

        private static final SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    /**
     * 每个线程负责一次消息推送
     */
    private static class PreThreadExecutorService implements Executor {

        private static final PreThreadExecutorService INSTANCE = new PreThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private static class BaseEventContext implements EventContext {

        private final String eventBusName;

        private final Subscriber subscriber;

        private final Object event;

        public BaseEventContext(String eventBusName, Subscriber subscriber, Object event) {
            this.eventBusName = eventBusName;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public String getSource() {
            return this.eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return this.subscriber != null ? subscriber.getSubscribeObject() : null;
        }

        @Override
        public Method getSubscribe() {
            return this.subscriber != null ? subscriber.getSubscribeMethod() : null;
        }

        @Override
        public Object getEvent() {
            return this.event;
        }
    }
}
