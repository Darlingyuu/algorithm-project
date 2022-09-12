package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/12 16:53
 * 存在重复元素：给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {2,14,18,22,22};
        Solution solution = new Solution();
        System.out.println(solution.containsDuplicate2(nums));
    }

    static class Solution {
        /**
         * 排序后对相邻的数字进行比较，如果有相同的，那么相同的值肯定是挨着的
         * @param nums
         * @return
         */
        public boolean containsDuplicate(int[] nums) {
            if (nums==null || nums.length==1){
                return false;
            }
            // 对数组进行排序
            Arrays.sort(nums);
            // 遍历数组
            int p=0,q=p;
            for (int i = 0; i< nums.length-1; i++) {
                p=nums[i];
                q=nums[i+1];
                if (p==q){
                    return true;
                }
            }
            return false;
        }

        /**
         * 利用set集合进行去重
         * 将每个数字都放入set中，如果存在重复的，那么放入set时会返回false
         * @param nums
         * @return
         */
        public boolean containsDuplicate2(int[] nums) {
            Set<Integer> set = new HashSet<>();
            // 遍历数组，将每个数放入set中，并放入时判断是否有重复
            for (int i = 0; i < nums.length; i++) {
                if(!set.add(nums[i])){
                    return true;
                }
            }
            return false ;
        }
    }
}
