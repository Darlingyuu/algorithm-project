package daily;

/**
 * 167. 两数之和 II - 输入有序数组[medium]
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/
 */
public class TwoSumII {
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            // 两指针，一个指向头，一个指向尾，向中间移动
            int left=0,right=numbers.length-1;

            while (left<right){
                if (numbers[left]+numbers[right]==target){
                    // 找到相加之和等于目标数 target 的两个数
                    return new int[]{left+1,right+1}; //下标要从1开始
                }else if (numbers[left]+numbers[right]<target){
                    // 相加之和小于target，left需要向右移，和增大
                    left++;
                }else {
                    // 相加之和大于target，right需要向左移，和减小
                    right--;
                }
            }
            return null;
        }
    }
}
