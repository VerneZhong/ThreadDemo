package com.zhongxb.concurrent.chapter25;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LRU其实是数据冷热治理的一种思想，不常用的数据为冷数据，常用的为热数据，对冷数据分配很少的资源或提前释放，可以帮助我们节省很多资源
 * LRUCache的实现方式有很多种，这里使用双向链表+hash表的方式来实现
 * @author Mr.zxb
 * @date 2018-11-01 10:53
 */
public class SoftLRUCache<K, V> {

    /**
     * 用于记录key值的顺序
     */
    private final LinkedList<K> keyList = new LinkedList<>();

    /**
     * cache的最大容量
     */
    private int capacity = 2 << 3;

    /**
     * 用于存放数据
     */
    private final Map<K, SoftReference<V>> cache = new HashMap<>(capacity);

    /**
     * CacheLoader接口提供了一种加载数据的方式
     */
    private final CacheLoader<K, V> cacheLoader;

    public SoftLRUCache(int capacity, CacheLoader<K, V> cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    /**
     * 存数据
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        // 当元素数量超过容量时，将最老的数据清除
        if (keyList.size() >= capacity) {
            // 旧的数据
            K eldestKey = keyList.removeFirst();
            cache.remove(eldestKey);
        }
        // 如果数据已经存在，则从key的队列中删除
        if (keyList.contains(key)) {
            keyList.remove(key);
        }
        // 将key存放至队尾
        keyList.addLast(key);
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * 取数据
     * @param key
     * @return
     */
    public V get(K key) {
        V value;
        // 先将key从keyList中删除
        boolean success = keyList.remove(key);
        // 如果删除失败则表明该数据不存在
        if (!success) {
            // 通过CacheLoader对数据进行加载
            value = cacheLoader.load(key);
            // 调用put方法cache数据
            this.put(key, value);
        } else {
            // 如果删除成功，则从cache中返回数据，并且将key再次放到队尾
            value = cache.get(key).get();
            keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }
}
