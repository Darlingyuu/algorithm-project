package daily;

import java.util.*;

/**
 * 2475. 数组中不等三元组的数目[easy]
 * https://leetcode.cn/problems/number-of-unequal-triplets-in-array/
 */
public class UnequalTriplets {
    class Solution {
        public int unequalTriplets(int[] nums) {
            // key=num value=个数
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int v : nums) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }

            int ans=0;
            // 记录枚举元素的左侧元素个数
            int a=0;
            int n = nums.length;
            for (Integer b : map.values()) {
                // 枚举的当前元素个数为b，左侧元素个数a,则右侧元素个数为n-a-b
                int c = n - a - b;
                ans+=a*b*c;
                a+=b;
            }
            return ans;
        }
    }
}
