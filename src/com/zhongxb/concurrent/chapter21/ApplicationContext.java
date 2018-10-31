package com.zhongxb.concurrent.chapter21;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 上下文
 * @author Mr.zxb
 * @date 2018-10-30 15:31
 */
public final class ApplicationContext {

    private ApplicationConfiguration configuration;

    private RuntimeInfo runtimeInfo;

    private ConcurrentHashMap<Thread, ApplicationContext> contexts = new ConcurrentHashMap<>();

    static class Holder {

        private static ApplicationContext instance = new ApplicationContext();

        private ApplicationConfiguration configuration = new ApplicationConfiguration();

        public static ApplicationContext getContext() {
            return Holder.instance;
        }

        public void setConfiguration(ApplicationConfiguration configuration) {
            this.configuration = configuration;
        }
    }

    static class RuntimeInfo {
    }

    static class ApplicationConfiguration {
    }
}
