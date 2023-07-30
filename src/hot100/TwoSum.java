package hot100;

import java.util.HashMap;

/**
 * 1. 两数之和[easy]
 * https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked
 */
public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            // key=需要的数字  value=当前数的下标  （需要的数+当前数=target）
            HashMap<Integer, Integer> map = new HashMap<>();

            // 遍历
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])){
                    // 当前数不在map中，则把与target的差值作为valuekey，下标作为value
                    // 加入map
                    map.put(target-nums[i],i);
                }else {
                    // 存在map中，说明和value中下标对应的值相加正好等于target
                    // 返回两个数的下标
                    return new int[]{map.get(nums[i]),i};
                }
            }
            return null;
        }
    }
}
