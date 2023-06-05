package daily;

import java.util.Arrays;

/**
 * 2460. 对数组执行操作[easy]
 * https://leetcode.cn/problems/apply-operations-to-an-array/
 */
public class ApplyOperations {
    public static void main(String[] args) {
        int[] nums={847,847,0,0,0,399,416,416,879,879,206,206,206,272};
        Solution solution = new Solution();
        int[] ints = solution.applyOperations(nums);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] applyOperations(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n-1; i++) {
                if (nums[i]==nums[i+1]){
                    nums[i]=2*nums[i];
                    nums[i+1]=0;
                    i++;
                }
            }

            int p=0,q=0;
            while (q<nums.length){
                if (nums[q]!=0){
                    nums[p]=nums[q];
                    p++;
                }
                q++;
            }
            //将后面的全都赋值为0
            for (; p < nums.length; p++) {
                nums[p]=0;
            }

            return nums;
        }

        public int[] applyOperationsII(int[] nums) {
            int n = nums.length;
            for (int i = 0, j = 0; i < n; i++) {
                if (i + 1 < n && nums[i] == nums[i + 1]) {
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                }
                if (nums[i] != 0) {
                    swap(nums, i, j);
                    j++;
                }
            }
            return nums;
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }
}
