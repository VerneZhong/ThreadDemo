package com.zhongxb.concurrent.chapter21;

/**
 * ThreadLocal设计线程上下文，第二个版本
 * @author Mr.zxb
 * @date 2018-10-31 11:03
 */
public class ActionContext2 {

    static class Configuration {
    }

    private static final ThreadLocal<Configuration> configuration = ThreadLocal.withInitial(Configuration::new);

    static class OtherResource {
    }

    private static final ThreadLocal<OtherResource> otherResource = ThreadLocal.withInitial(OtherResource::new);

    public static Configuration getConfiguration() {
        return configuration.get();
    }

    public static void setConfiguration(Configuration conf) {
        configuration.set(conf);
    }

    public static OtherResource getOtherResource() {
        return otherResource.get();
    }

    public static void setOtherResource(OtherResource resource) {
        otherResource.set(resource);
    }


}
