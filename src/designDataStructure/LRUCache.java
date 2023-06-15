package designDataStructure;

/**
 * 146. LRU 缓存[medium]
 * https://leetcode.cn/problems/lru-cache/
 */


import java.util.LinkedHashMap;

/**
 * 使用内置的LinkedHashMap
 */
public class LRUCache {
    // 容量
    int cap;
    // 缓存
    LinkedHashMap<Integer,Integer> cache=new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.cap=capacity;
    }

    public int get(int key) {
        // 若缓存中不存在
        if (!cache.containsKey(key)){
            return -1;
        }
        // 存在，将该key变为最近使用
        makeRecently(key);
        // 返回对应value
        return cache.get(key);
    }

    public void put(int key, int value) {
        // 若当前缓存中已存在该key
        if (cache.containsKey(key)){
            // 修改key的值
            cache.put(key,value);
            // 该key变为最近使用
            makeRecently(key);
            return;
        }

        // 若缓存中不存在该key
        if (cache.size()>=this.cap){ // 缓存满了
            // 链表头部就是最久未使用的 key，删除
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的key添加到链表尾部
        cache.put(key,value);
    }


    // 将该key变为最近使用
    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除key，重新插入到队尾
        cache.remove(key);
        cache.put(key,val);
    }
}
