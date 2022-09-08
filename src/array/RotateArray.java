package array;

import java.util.Arrays;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/8 22:17
 * 旋转数组:
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2skh7/
 */
public class RotateArray {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] nums = {1, 2};
        Solution solution = new Solution();
        solution.rotate(nums, 2);
//        solution.rotate2(nums, 2);
        System.out.println(Arrays.toString(nums));
    }


    static class Solution {
        /**
         * 采用临时数组存储
         *
         * @param nums
         * @param k
         */
        public void rotate(int[] nums, int k) {
            // k=0不用交换位置
            if (k == 0) {
                return;
            }
            // 定义临时数组
            int[] temp = new int[nums.length + k];
            // 将nums元素放到temp后面，前面留k个空位用于轮转
            for (int i = 0; i < nums.length; i++) {
                temp[k + i] = nums[i];
            }
            // 记录第一个元素位置和最后一个轮转的元素位置
            int p = k, q = temp.length - 1;
            // 轮转k次,每次将q位置的元素放到p前面
            for (int i = 1; i <= k; i++) {
                temp[p - 1] = temp[q];
                q--;
                p--;
            }

            // 将temp中元素复制回nums
            for (int i = 0; i < nums.length; i++) {
                nums[i] = temp[i];
            }
        }

        /**
         * 采用临时数组存储  优化版
         *
         * @param nums
         * @param k
         */
        public void rotate1(int[] nums, int k) {
            // k=0不用交换位置
            if (k == 0) {
                return;
            }
            int length = nums.length;
            int temp[] = new int[length];
            //把原数组值放到一个临时数组中，
            for (int i = 0; i < length; i++) {
                temp[i] = nums[i];
            }
            //然后在把临时数组的值重新放到原数组，并且往右移动k位
            for (int i = 0; i < length; i++) {
                nums[(i + k) % length] = temp[i];
            }
        }

        /**
         * 多次反转：先反转全部数组，在反转前k个，最后在反转剩余的
         * @param nums
         * @param k
         */
        public void rotate2(int[] nums, int k) {
            // k=0不用交换位置
            if (k == 0) {
                return;
            }
            int length = nums.length;
            k %= length;
            reverse(nums, 0, length - 1);//先反转全部的元素
            reverse(nums, 0, k - 1);//在反转前k个元素
            reverse(nums, k, length - 1);//接着反转剩余的
        }
        //把数组中从[start，end]之间的元素两两交换,也就是反转
        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start++] = nums[end];
                nums[end--] = temp;
            }
        }




        /**
         * 原地反转，每次都挪动元素的位置
         * 超出时间限制，不可取
         * @param nums
         * @param k
         */
//        public void rotate2(int[] nums, int k) {
//            // 轮转k次
//            for (int i = 1; i <= k; i++) {
//                // 记录数组中最后一个元素
//                int temp = nums[nums.length - 1];
//                // 前面nums.length-1个元素都向后移动一位
//                for (int j = nums.length - 1; j > 0; j--) {
//                    nums[j] = nums[j - 1];
//                }
//                nums[0] = temp;
//            }
//        }


    }
}
