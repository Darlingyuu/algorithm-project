package dp;

/**
 * 188. 买卖股票的最佳时机 IV[hard]
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class MaxProfitIV {
    public static void main(String[] args) {
        int[] prices={3,2,6,5,0,3};
        Solution solution = new Solution();
//        System.out.println(solution.maxProfit(prices));
        System.out.println(solution.maxProfit(2,prices));
    }


    static class Solution {
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][][] dp=new int[n][k+1][2];

            // 最多k笔交易
            // 不持有股票（前一天也不持有 或 把股票今天卖了）
            // dp[i][k][0]=max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
            // 持有股票（前一天也持有 或 前一天也不持有今天才买的）前面i-1天的最大购买限制k-1,
            // dp[i][k][1]=max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])
            for (int i = 0; i < n; i++) {
                for (int j = k; j >=1; j--) {
                    //base case
                    if (i==0){
                        dp[0][j][0]=0;
                        dp[0][j][1]=-prices[0];
                        continue;
                    }
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
                }
            }
            return dp[n-1][k][0];
        }
    }
}
