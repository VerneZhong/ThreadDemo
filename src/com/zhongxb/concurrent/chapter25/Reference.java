package com.zhongxb.concurrent.chapter25;

/**
 * 引用类
 * 当Reference对象被实例化后，会在堆内存中创建1M+（+代表的是除了byte[] data之外Reference对象自身占有的少许内存）的内存空间
 * @author Mr.zxb
 * @date 2018-11-01 10:25
 */
public class Reference {

    /**
     * 1M
     */
    private final byte[] data = new byte[2 << 19];

    /**
     * 会在垃圾回收的标记阶段被调用
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("The reference will be GC.");
    }
}
