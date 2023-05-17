package dp;

/**
 * 123. 买卖股票的最佳时机 III[hard]
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class MaxProfitIII {
    public static void main(String[] args) {
        int[] prices={3,3,5,0,0,3,1,4};
        Solution solution = new Solution();
//        System.out.println(solution.maxProfit(prices));
        System.out.println(solution.maxProfitI(prices));
    }


    static class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][][] dp=new int[n][3][2];

            // 最多买卖两笔 k=2
            // 不持有股票（前一天也不持有 或 把股票今天卖了）
            // dp[i][k][0]=max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
            // 持有股票（前一天也持有 或 前一天也不持有今天才买的）前面i-1天的最大购买限制k-1,
            // dp[i][k][1]=max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])
            for (int i = 0; i < n; i++) {
                for (int k = 2; k >=1; k--) {
                    //base case
                    if (i==0){
                        dp[0][k][0]=0;
                        dp[0][k][1]=-prices[0];
                        continue;
                    }
                    dp[i][k][0]=Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                    dp[i][k][1]=Math.max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]);
                }
            }
            return dp[n-1][2][0];
        }

        // 状态转移方程：
        // dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
        // dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
        // dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
        // dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
        public int maxProfitI(int[] prices) {
            int n = prices.length;
            int dp_i_2_0=0,dp_i_2_1=Integer.MIN_VALUE;
            int dp_i_1_0=0,dp_i_1_1=Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                dp_i_2_0=Math.max(dp_i_2_0,dp_i_2_1+prices[i]);
                dp_i_2_1=Math.max(dp_i_2_1,dp_i_1_0-prices[i]);
                dp_i_1_0=Math.max(dp_i_1_0,dp_i_1_1+prices[i]);
                dp_i_1_1=Math.max(dp_i_1_1,-prices[i]);
            }
            return dp_i_2_0;
        }
    }
}
