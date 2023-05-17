package dp;

/**
 * 714. 买卖股票的最佳时机含手续费[medium]
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class MaxProfitVI {
    public static void main(String[] args) {
        int[] prices={1, 3, 2, 8, 4, 9};
        Solution solution = new Solution();
        System.out.println(solution.maxProfitI(prices,2));
    }


    static class Solution {
        public int maxProfit(int[] prices, int fee) {
            int n = prices.length;
            int[][] dp=new int[n][2];

            // 每笔交易都需要付手续费
            // 不持有股票（前一天也不持有 或 把股票今天卖了）
            // dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i])
            // 持有股票（前一天也持有 或 前两天也不持有今天才买的，考虑冷冻期）
            // dp[i][1]=max(dp[i-1][1],dp[i-1][0]-prices[i]-fee)
            for (int i = 0; i < n; i++) {
                //base case
                if (i==0){
                    dp[0][0]=0;
                    dp[0][1]=-prices[0]-fee;
                    continue;
                }
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]-fee);
            }
            return dp[n-1][0];
        }

        public int maxProfitI(int[] prices, int fee) {
            int n = prices.length;

            int i_0=0,i_1=Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int tmp=i_0;
                i_0=Math.max(i_0,i_1+prices[i]);
                i_1=Math.max(i_1,tmp-prices[i]-fee);
            }
            return i_0;
        }
    }
}
