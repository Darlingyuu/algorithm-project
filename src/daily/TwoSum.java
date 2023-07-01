package daily;

import java.util.HashMap;

/**
 * 1. 两数之和[easy]
 * https://leetcode.cn/problems/two-sum/
 */
public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    // 当前数不在map中，则把与target的差值作为valuekey，下标作为value
                    map.put(target - nums[i],i);
                }else {
                    // 存在map中，说明和value中下标对应的值相加正好等于target
                    return new int[]{map.get(nums[i]),i};
                }
            }
            return null;
        }
    }
}
