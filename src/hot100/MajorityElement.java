package hot100;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 169. 多数元素[easy]
 * https://leetcode.cn/problems/majority-element/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class MajorityElement {
    class Solution {
        public int majorityElement(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            int count = 0;
            int num = 0;
            for (Integer key : map.keySet()) {
                if (count < map.get(key)) {
                    count = map.get(key);
                    num = key;
                }
            }
            return num;
        }

        // 按照单调递增或单调递减的顺序排序，那么中间的数就是众数
        public int majorityElementII(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }

        // 投票算法
        public int majorityElementIII(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }
}
