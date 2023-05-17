package dp;

/**
 * 121. 买卖股票的最佳时机[easy]
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class MaxProfit {
    public static void main(String[] args) {
        int[] prices={7,1,5,3,6,4};
        Solution solution = new Solution();
//        System.out.println(solution.maxProfit(prices));
        System.out.println(solution.maxProfitI(prices));
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp=new int[prices.length][2];
            // 只能买卖一次
            // 不持有股票（前一天也不持有 或 把股票今天卖了）
            // dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i])
            // 持有股票（前一天也持有 或 今天才买的）
            // dp[i][1]=max(dp[i-1][1],-prices[i])

            for (int i = 0; i < n; i++) {
                //base case
                if (i==0){
                    dp[i][0]=0;
                    dp[i][1]=-prices[i];
                    continue;
                }
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[i-1][1],-prices[i]);
            }
            return dp[n-1][0];
        }

        public int maxProfitI(int[] prices) {
            int n = prices.length;
            int dp_i_0=0,dp_i_1=Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                dp_i_0=Math.max(dp_i_0,dp_i_1+prices[i]);
                dp_i_1=Math.max(dp_i_1,-prices[i]);
            }
            return dp_i_0;
        }
    }
}
