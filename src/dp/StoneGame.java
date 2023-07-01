package dp;

/**
 * 877. 石子游戏[medium]
 * https://leetcode.cn/problems/stone-game/
 */
public class StoneGame {

    class Solution {
        public boolean stoneGame(int[] piles) {
            int n = piles.length;
            // dp[i][j][0] 先手
            // dp[i][j][1] 后手
            int[][][] dp = new int[n][n][2];
            for (int i = 0; i < n; i++) {
                dp[i][i][0] = piles[i];
                dp[i][i][1] = 0;
            }

            for (int i = n - 2; i >= 0; i--) {  // [n-2.n-1]
                for (int j = i + 1; j < n; j++) {
                    // 先手
                    // 选左边,下一次[i+1,j]
                    int left = piles[i] + dp[i + 1][j][1];
                    // 选右边，下一次[i,j-1]
                    int right = piles[j] + dp[i][j - 1][1];
                    if (left > right) {
                        dp[i][j][0] = left;
                        dp[i][j][1] = dp[i + 1][j][0];
                    } else {
                        dp[i][j][0] = right;
                        dp[i][j][1] = dp[i][j - 1][0];
                    }
                }
            }
            return dp[0][n-1][0]>=dp[0][n-1][1];
        }
    }
}
