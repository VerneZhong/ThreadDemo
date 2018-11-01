package com.zhongxb.concurrent.chapter25;

/**
 * @author Mr.zxb
 * @date 2018-11-01 10:58
 */
@FunctionalInterface
public interface CacheLoader<K, V> {

    /**
     * 定义加载数据的方法
     * @param k
     * @return
     */
    V load(K k);
}
