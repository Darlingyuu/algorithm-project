package tree;

import java.util.Arrays;

/**
 * 912. 排序数组[medium]
 * https://leetcode.cn/problems/sort-an-array/
 */
public class SortArray {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] sortArray(int[] nums) {
            Arrays.sort(nums);  // 底层用的是快排
            return nums;
        }
    }
}
