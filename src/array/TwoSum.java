package array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/26 21:10
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums={2,7,11,15};
        Solution solution = new Solution();
        int[] ints = solution.twoSumII(nums, 9);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        /**
         * 双层循环   O（n2)
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                int sub=target-nums[i];
                for (int j = i+1; j < nums.length; j++) {
                    if (sub==nums[j]){
                        return new int[]{i,j};
                    }
                }
            }

            return null;
        }

        /**
         * 用HashMap   O（n)
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSumII(int[] nums, int target) {
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
