package hot100;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 146.LRU 缓存[medium]
 * https://leetcode.cn/problems/lru-cache/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class LRUCache {
    private int cap;
    private LinkedList<Integer> queue;
    private HashMap<Integer, Integer> map;

    public LRUCache(int capacity) {
        cap=capacity;
        queue = new LinkedList<>();
        map = new HashMap<>();
    }

    public int get(int key) {
        Integer val = map.getOrDefault(key, -1);
        if (val!=-1){
            queue.remove(Integer.valueOf(key));
            queue.addFirst(key);
        }
        return val;
    }

    public void put(int key, int value) {
        if (map.get(key)!=null){
            // 更新
            queue.remove(Integer.valueOf(key));
        }else {
            // 添加
            if  (queue.size()==cap){
                Integer last = queue.removeLast();
                map.remove(last);
            }
        }
        queue.addFirst(key);
        map.put(key,value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(1,1);
        cache.put(2,3);
        cache.put(4,1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

}
