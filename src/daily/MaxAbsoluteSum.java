package daily;

/**
 * 1749. 任意子数组和的绝对值的最大值[medium]
 * https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/
 */
public class MaxAbsoluteSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, -5, 1, -4, 3, -2};
        System.out.println(solution.maxAbsoluteSum(nums));
    }

    static class Solution {
        public int maxAbsoluteSumII(int[] nums) {
            int f = 0, g = 0;
            int ans = 0;
            for (int x : nums) {
                f = Math.max(f, 0) + x;
                g = Math.min(g, 0) + x;
                ans = Math.max(ans, Math.max(f, Math.abs(g)));
            }
            return ans;
        }


        public int maxAbsoluteSum(int[] nums) {
            /**
             * 一个变量绝对值的最大值，可能是这个变量的最大值的绝对值，也可能是这个变量的最小值的绝对值。
             * 题目要求任意子数组和的绝对值的最大值，可以分别求出子数组和的最大值 positiveMax 和子数组和的最小值 negativeMin
             * 最后返回max(positiveMax.-negativeMin)
             */
            int positiveMax = 0, negativeMin = 0;
            int positiveSum = 0, negativeSum = 0;
            for (int num : nums) {
                // 加上当前元素
                positiveSum += num;
                // 取较大值
                positiveMax = Math.max(positiveMax, positiveSum);
                // 如果positiveSum<0 则将其置为0，不再累计前面的和
                positiveSum = Math.max(0, positiveSum);

                // 加或不加当前元素，取较小值
                negativeSum += num;
                negativeMin = Math.min(negativeMin, negativeSum);
                negativeSum = Math.min(0, negativeSum);
            }
            return Math.max(positiveMax, -negativeMin);
        }
    }
}
