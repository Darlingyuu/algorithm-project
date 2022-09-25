package array;

import java.util.Arrays;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/25 21:36
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class MoveZeroes {
    public static void main(String[] args) {
//        int[] nums={0,1,0,3,12};
        int[] nums={0};
        Solution solution = new Solution();
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {

        /**
         * 双指针 记录0和非0的位置，交换
         * @param nums
         */
        public void moveZeroes(int[] nums) {
            int i = 0;//统计前面0的个数
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == 0) {//如果当前数字是0就不操作
                    i++;
                } else if (i != 0) {
                    //否则，把当前数字放到最前面那个0的位置，然后再把
                    //当前位置设为0
                    nums[j - i] = nums[j];
                    nums[j] = 0;
                }
            }

        }

        /**
         * 题解版
         * 把非0的往前挪，挪完之后，后面的就都是0了，然后在用0覆盖后面的
         * @param nums
         */
        public void moveZeroesII(int[] nums) {
            if (nums==null || nums.length==0){
                return;
            }
            int index=0;

            // 一次遍历，都把非零的往前挪
            for (int i = 0; i < nums.length; i++) {
                if (nums[i]!=0){
                    nums[index++]=nums[i];
                }
            }
            // 后面都是0
            while (index<nums.length){
                nums[index++]=0;
            }
        }
    }
}
