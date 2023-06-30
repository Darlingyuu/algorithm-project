package dp;

/**
 * 312. 戳气球[hard]
 * https://leetcode.cn/problems/burst-balloons/
 */
public class MaxCoins {

    class Solution {
        public int maxCoins(int[] nums) {
            int n = nums.length;
            // 添加两侧的虚拟气球
            int[] points = new int[n + 2];
            points[0] = points[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                points[i]=nums[i-1];
            }

            // base case 已经都被初始化为 0
            int[][] dp = new int[n + 2][n + 2];
            // 开始状态转移
            // i 应该从下往上
            for (int i = n; i >=0; i--) {
                // j 应该从左往右
                for (int j = i + 1; j < n + 2; j++) {
                    // 开区间(i,j),最后戳破的气球k
                    for (int k = i+1; k < j; k++) {
                        // 择优做选择
                        dp[i][j]=Math.max(dp[i][j],
                                dp[i][k]+dp[k][j]+points[i]*points[j]*points[k]);
                    }
                    /**
                     * 要最后戳破气球k吗？
                     * 那得先把开区间(i, k)的气球都戳破，
                     * 再把开区间(k, j)的气球都戳破；
                     * 最后剩下的气球k，相邻的就是气球i和气球j，这时候戳破k的话得到的分数就是points[i]*points[k]*points[j]。
                     * dp[i][k]+dp[k][j]+points[i]*points[j]*points[k]
                     */
                }
            }
            return dp[0][n + 1];
        }
    }
}
