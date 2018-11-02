package com.zhongxb.concurrent.chapter27;

import com.zhongxb.concurrent.chapter19.Future;

import java.lang.reflect.Method;

/**
 * @author Mr.zxb
 * @date 2018-11-02 17:29
 */
class ActiveMessage {

    /**
     * 接口方法中的参数
     */
    private final Object[] objects;

    /**
     * 接口方法
     */
    private final Method method;

    /**
     * 具体的Service接口
     */
    private Object service;

    /**
     * 有返回值的方法，会返回ActiveFuture类型
     */
    private final ActiveFuture<Object> future;

    private ActiveMessage(Builder builder) {
        this.objects = builder.objects;
        this.method = builder.method;
        this.future = builder.future;
        this.service = builder.service;
    }

    /**
     * 通过反射的方式调用执行的具体实现
     */
    public void execute() {
        try {
            // 执行接口的方法
            Object result = method.invoke(service, objects);
            if (future != null) {
                // 如果是有返回值的接口方法，则需要通过get方法获得最终的结果
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                // 将结果交给ActiveFuture，接口方法的线程会得到返回
                future.finish(realResult);
            }
        } catch (Exception e) {
            // 如果发生异常，那么有返回值的方法将会显式地指定为null，无返回值的接口方法则会忽略该异常
            if (future != null) {
                future.finish(null);
            }
        }
    }

    /**
     * 主要对ActiveMessage的构建
     */
    private class Builder {
        private Object[] objects;
        private Method method;
        private ActiveFuture<Object> future;
        private Object service;

        public Builder useMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder returnFuture(ActiveFuture<Object> future) {
            this.future = future;
            return this;
        }

        public Builder withObjects(Object[] objects) {
            this.objects = objects;
            return this;
        }

        public Builder forService(Object service) {
            this.service = service;
            return this;
        }

        /**
         * 构建实例
         * @return
         */
        public ActiveMessage build() {
            return new ActiveMessage(this);
        }

    }
}
