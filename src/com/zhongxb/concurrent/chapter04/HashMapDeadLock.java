package com.zhongxb.concurrent.chapter04;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapDeadLock {

    private final Map<String, String> map = new ConcurrentHashMap<>(16);

    public void add(String key, String val) {
        this.map.put(key, val);
    }

    public static void main(String[] args) {

        HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
                    hashMapDeadLock.add(j+"", j+"");
                }
            }).start();
        }
    }

}
