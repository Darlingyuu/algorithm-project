package hot100;

import java.util.Arrays;

/**
 * 283. 移动零[easy]
 * https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
 */
public class MoveZeroes {

    class Solution {
        public void moveZeroes(int[] nums) {

            // 双指针
            int p=0,q=0;
            while (q<nums.length){
                if (nums[q]!=0){
                    // 将q指向的非零数字移动到p位置
                    nums[p]=nums[q];
                    // p后移一位
                    p++;
                }
                // q不断后移，直到指向非零数字
                q++;
            }

            // q遍历结束，p指向非零数字的后一位 [0,q-1]范围全部是非零数字
            // 将后面的数字归零
            Arrays.fill(nums,p,nums.length,0);
        }
    }
}

