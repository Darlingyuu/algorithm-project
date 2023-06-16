package designDataStructure;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * 460. LFU 缓存[hard]
 * https://leetcode.cn/problems/lfu-cache/
 * 最不经常使用
 */
public class LFUCache {

    // key到val的映射
    HashMap<Integer,Integer> keyToVal;
    // key到freq的映射
    HashMap<Integer,Integer> keyToFreq;
    // freq到key列表的映射 列表需要维护key插入顺序
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 记录最小的频次
    int minFreq;
    // 记录LFU缓存的最大容量
    int cap;

    /**
     * 构造函数
     * @param capacity 容量
     */
    public LFUCache(int capacity) {
        keyToVal=new HashMap<>();
        keyToFreq=new HashMap<>();
        freqToKeys=new HashMap<>();
        this.cap=capacity;
        this.minFreq=0;
    }

    /**
     * 返回key对应的val，freq+1
     * @param key key
     * @return 返回key对应的val
     */
    public int get(int key) {
        // 如果key不存在，返回-1
        if (!keyToVal.containsKey(key)){
            return -1;
        }
        // 增加 key 对应的 freq
        increaseFreq(key);
        return keyToVal.get(key);
    }

    /**
     * 若key存在，修改key对应的val，freq+1
     * 若key不存在，则需要新插入key(若容量已满，淘汰freq最小的key),插入key val ,freq=1
     * @param key key
     * @param value value
     */
    public void put(int key, int value) {
        // 非法容量
        if (this.cap<0) return;

        // 若key存在，修改key对应的val，freq+1
        if (keyToVal.containsKey(key)){
            keyToVal.put(key,value);
            increaseFreq(key);
            return;
        }

        // 若key不存在，则需要新插入key
        // 若容量已满，淘汰freq最小的key
        if (this.cap<=keyToVal.size()){
            removeMinFreqKey();
        }

        // 插入key val ,freq=1
        keyToVal.put(key,value);
        keyToFreq.put(key,1);
        // public V putIfAbsent(K key, V value)  key存在不操作，key不存在就插入key-value
        freqToKeys.putIfAbsent(1,new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        // 插入新 key 后最小的 freq 肯定是 1
        this.minFreq = 1;
    }

    // key对应的freq+1
    private void increaseFreq(int key) {
        Integer freq = keyToFreq.get(key);
        // 更新keyToFreq
        keyToFreq.put(key,freq+1);

        // 更新freqToKeys
        // 将 key 从 freq 对应的列表中删除
        freqToKeys.get(freq).remove(key);
        // 将key加入freq+1的对应列表中
        freqToKeys.putIfAbsent(freq+1,new LinkedHashSet<>());
        freqToKeys.get(freq+1).add(key);
        // 如果 freq 对应的列表空了，移除这个 freq
        if (freqToKeys.get(freq).isEmpty()){
            freqToKeys.remove(freq);
            // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if (freq==this.minFreq){
                this.minFreq++;
            }
        }
    }

    // 淘汰freq最小的key
    private void removeMinFreqKey() {
        // freq最小的key列表
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        // 其中最先被插入的那个 key 就是该被淘汰的 key
        Integer deleteKey = keyList.iterator().next();
        // 更新freqToKeys
        keyList.remove(deleteKey);
        if (keyList.isEmpty()){
            // minFreq频次的key不存在，直接整个删除
            freqToKeys.remove(this.minFreq);
            // 此处minFreq不需要更新，removeMinFreqKey()只会在put()方法中要新插入数据时调用，minFreq会被更新为1
        }

        // 更新keyToVal
        keyToVal.remove(deleteKey);
        // 更新keyToFreq
        keyToFreq.remove(deleteKey);
    }
}
