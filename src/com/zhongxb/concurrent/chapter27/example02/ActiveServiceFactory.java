package com.zhongxb.concurrent.chapter27.example02;

import com.zhongxb.concurrent.chapter19.Future;
import com.zhongxb.concurrent.chapter27.example01.ActiveFuture;
import com.zhongxb.concurrent.chapter27.example01.ActiveMessageQueue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通用Active Objects的核心类，其负责生成Service的代理以及构建ActiveMessage
 * @author Mr.zxb
 * @date 2018-11-02 17:47
 */
public class ActiveServiceFactory {

    /**
     * 定义ActiveMessageQueue，用于存放ActiveMessage
     */
    private static final ActiveMessageQueue<ActiveMessage> queue = new ActiveMessageQueue();

    public static <T> T active(T instance) {
        // 生成Service的代理类
        Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), new ActiveInvocationHandler<>(instance));
        return (T) proxy;
    }

    private static class ActiveInvocationHandler<T> implements InvocationHandler {

        private final T instance;

        public ActiveInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // 如果接口方法被@ActiveMessage标记，则会转换为ActiveMessage
            if (method.isAnnotationPresent(ActiveMethod.class)) {
                // 检查该方法是否符合规范
                this.checkMethod(method);

                ActiveMessage.Builder builder = new ActiveMessage.Builder();
                builder.useMethod(method).withObjects(args).forService(instance);

                Object result = null;
                if (this.isReturnFutureType(method)) {
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture<Object>) result);
                }
                // 将ActiveMessage加入至队列中
                queue.offer(builder.build());
                return result;
            } else {
                // 如果是普通方法，则会正常执行
                return method.invoke(instance, args);
            }
        }

        /**
         * 检查有返回值的方法是否为Future，否则将会抛出异常
         * @param method
         */
        private void checkMethod(Method method) throws IllegalActiveMethod {
            // 有返回值，必须是ActiveFuture类型的返回值
            if (!isReturnVoidType(method) && !isReturnFutureType(method)) {
                throw new IllegalActiveMethod("The method [" + method.getName() + "] return type must be void/Future");
            }
        }

        /**
         * 判断返回值是否为Future返回类型
         * @param method
         * @return
         */
        private boolean isReturnFutureType(Method method) {
            return method.getReturnType().isAssignableFrom(Future.class);
        }

        /**
         * 判断方法是否无返回类型
         * @param method
         * @return
         */
        private boolean isReturnVoidType(Method method) {
            return method.getReturnType().equals(Void.TYPE);
        }
    }
}
