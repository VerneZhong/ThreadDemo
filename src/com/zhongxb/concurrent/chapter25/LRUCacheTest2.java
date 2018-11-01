package com.zhongxb.concurrent.chapter25;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-11-01 11:16
 */
public class LRUCacheTest2 {

    public static void main(String[] args) throws InterruptedException {

//        LRUCache<Integer, Reference> cache = new LRUCache<>(200, key -> new Reference());
        SoftLRUCache<Integer, Reference> cache = new SoftLRUCache<>(200, key -> new Reference());
        System.out.println(cache);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cache.get(i);
//            TimeUnit.SECONDS.sleep(1);
            System.out.println("The " + i + " reference stored at cache.");
        }
    }
}
