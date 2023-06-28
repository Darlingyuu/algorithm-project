package dp;

/**
 * 416. 分割等和子集[medium]
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 */
public class CanPartition {
    class Solution {

        public boolean canPartitionII(int[] nums) {
            int n = nums.length;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 和为奇数时，不成立
            if (sum % 2 == 1) return false;
            sum = sum / 2;

            // dp[i]=true记录nums中有一个子集的元素和为i
            // 所需答案就是dp[sum]
            boolean[] dp = new boolean[sum + 1];
            // base case
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                for (int j = sum; j >= 0; j--) { // 从后往前遍历
                    if (j>=nums[i]){
                        // dp[j] || dp[j-nums[i]];  都指上一次i-1的结果
                        dp[j]= dp[j] || dp[j-nums[i]];
                    }
                }
            }
            return dp[sum];
        }


        public boolean canPartition(int[] nums) {
            int n = nums.length;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 和为奇数时，不成立
            if (sum % 2 == 1) return false;
            sum = sum / 2;

            // dp[i][j]=true记录nums[0,i-1]中有一个子集的元素和为j
            // 所需答案就是dp[n][sum]
            boolean[][] dp = new boolean[n + 1][sum + 1];
            // 初始化 空子集元素和为0
            for (int i = 0; i < n + 1; i++) {
                dp[i][0] = true;
            }


            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= sum; j++) {
                    if (j < nums[i - 1]) { // 元素和j小于元素值,结果与上一次扩大范围结果保持一致
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        // 加入该元素nums[i-1]，那上一次为dp[i-1][j-nums[i-1]]
                        // 不加，上一次dp[i-1][j]
                        // 两种情况任一成立即可
                        dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                    }
                }
            }

            return dp[n][sum];
        }
    }
}
