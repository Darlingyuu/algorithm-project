package dp;

/**
 * 518. 零钱兑换 II[medium]
 * https://leetcode.cn/problems/coin-change-ii/
 */
public class CoinChangeII {

    class Solution {
        public int change(int amount, int[] coins) {
            int n = coins.length;
            // dp[i][j] 记录用coins[0,i-1]的硬币面值凑成总金额为j的组合个数
            int[][] dp = new int[n+1][amount + 1];
            // 0个硬币，金额为0
            // 金额为0，不凑就是一种
            for (int i = 0; i <= n; i++) {
                dp[i][0]=1;
            }

            // 选择1 硬币
            for (int i = 1; i <= n; i++) {
                // 选择2 总金额
                for (int j = 1; j <= amount; j++) {
                    if (j>=coins[i-1]){  // 总金额大于当前硬币面值 才可以凑
                        // 选择coins[i-1]该硬币,凑法和coins[0,i-1]凑出j-coins[i-1]金额的数量一致(可重复使用)
                        // 不选择该硬币，凑法和coins[0,i-2]凑出j金额的数量一致
                        dp[i][j]=dp[i][j-coins[i-1]]+dp[i-1][j];
                    }else {
                        dp[i][j]=dp[i-1][j];
                    }
                }
            }
            return dp[n][amount];
        }
    }
}
