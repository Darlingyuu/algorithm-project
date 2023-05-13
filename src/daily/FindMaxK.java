package daily;

import java.util.Arrays;

/**
 * 2441. 与对应负数同时存在的最大正整数[easy]
 * 20230513
 * https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative/
 */
public class FindMaxK {
    public static void main(String[] args) {
//        int[] nums={-1,10,6,7,-7,1};
        int[] nums={-10,8,6,7,-2,-3};
        Solution solution = new Solution();
        int k = solution.findMaxK(nums);
        System.out.println(k);
    }

    static class Solution {
        public int findMaxK(int[] nums) {
            Arrays.sort(nums);

            int p=0,q=nums.length-1;
            while (nums[p]<0 && nums[q]>0){
                if (-nums[p]<nums[q]){
                    q--;
                }else if (-nums[p]>nums[q]){
                    p++;
                }else {
                    return nums[q];
                }
            }

            return -1;
        }
    }
}
