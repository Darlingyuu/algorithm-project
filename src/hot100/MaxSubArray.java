package hot100;

/**
 * 53. 最大子数组和[medium]
 * https://leetcode.cn/problems/maximum-subarray/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxSubArray {
    class Solution {
        public int maxSubArray(int[] nums) {
            int len = nums.length;
            // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
            int[] dp = new int[len];
            dp[0] = nums[0];

            int res = dp[0];
            for (int i = 1; i < nums.length; i++) {
                // 针对单个数字nums[i]
                // 如果上一个的最大和为整数，加上当前的数，那以当前数为结尾的最大和变大
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    // 如果上一个的最大和为负数或0，子数组中仅有当前的数，那以当前数为结尾的和最大
                    dp[i] = nums[i];
                }
                // 记录最大值
                res = Math.max(res, dp[i]);
            }

            return res;
        }
    }
}
