package array;

import java.util.Arrays;

/**
 * 283. 移动零[easy]
 * https://leetcode.cn/problems/move-zeroes/
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums={0,1,0,3,12};
//        int[] nums={0};
        Solution solution = new Solution();
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void moveZeroes(int[] nums) {
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
        }
    }
}
