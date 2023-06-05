package dp;

/**
 * 931. 下降路径最小和[medium]
 * https://leetcode.cn/problems/minimum-falling-path-sum/
 */
public class MinFallingPathSum {
    public static void main(String[] args) {
        int[][] matrix={{-84,-36,2},{87,-79,10},{42,10,63}};
        Solution solution = new Solution();
        System.out.println(solution.minFallingPathSum(matrix));
    }

    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            int[][] dp = new int[n][n];
            // 求和最小的下降路径，所以初始化为大值
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j]=Integer.MAX_VALUE;
                }
            }
            // base case
            // 初始化第一行
            for (int i = 0; i < n; i++) {
                dp[0][i]=matrix[0][i];
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 对于dp[i,j]来说，到达该位置的来源，dp[i-1][j-1]  dp[i-1][j]  dp[i-1][j+1]
                    int min=Integer.MAX_VALUE;
                    if (j!=0) {
                        min=Math.min(min,Math.min(dp[i-1][j-1],dp[i-1][j]));
                    }
                    if (j!=n-1){
                        min=Math.min(min,Math.min(dp[i-1][j],dp[i-1][j+1]));
                    }
                    dp[i][j]=min+matrix[i][j];
                }
            }

            // 得到dp[n-1]的最小值
            int res=Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                res=Math.min(res,dp[n-1][i]);
            }
            return res;
        }
    }
}
