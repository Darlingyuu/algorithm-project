package array;

import java.util.*;

/**
 * 380. O(1) 时间插入、删除和获取随机元素[medium]
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/
 */
public class RandomizedSet {
    // list底层是数组，增减都放最后时间复杂度才为O(1)
    // 存放所有的元素
    private List<Integer> elementList;
    // 记录所有元素和下标 key=元素值 value=下标
    private Map<Integer,Integer> map;
    private Random random=new Random();

    public RandomizedSet() {
        elementList=new ArrayList<>();
        map=new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)){
            return false;
        }else {
            // 放在list最后
            map.put(val,elementList.size());
            elementList.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        }else {
            // 将要删除的元素交换到数组末尾（交换时间复杂度O(1)）
            Integer index = map.get(val);
            Collections.swap(elementList,index,elementList.size()-1);
            // 被交换的元素在map中记录的下标需要更新
            map.put(elementList.get(index),index);
            // 删除末尾元素
            elementList.remove(elementList.size()-1);
            // 从map中删除记录
            map.remove(val);
            return true;
        }
    }

    public int getRandom() {
        // 元素个数
        int num = elementList.size();
        int rand = random.nextInt(num);
        return elementList.get(rand);
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(0);
        set.insert(1);
        set.remove(0);
        set.insert(2);
        set.remove(1);
        System.out.println(set.getRandom());
    }
}
