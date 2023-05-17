package dp;

/**
 * 309. 最佳买卖股票时机含冷冻期[medium]
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class MaxProfitV {
    public static void main(String[] args) {
        int[] prices={1,2,3,0,2};
        Solution solution = new Solution();
        System.out.println(solution.maxProfitI(prices));
    }


    static class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp=new int[n][2];

            // 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
            // 不持有股票（前一天也不持有 或 把股票今天卖了）
            // dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i])
            // 持有股票（前一天也持有 或 前两天也不持有今天才买的，考虑冷冻期）
            // dp[i][1]=max(dp[i-1][1],dp[i-2][0]-prices[i])
            for (int i = 0; i < n; i++) {
                //base case
                if (i==0){
                    dp[0][0]=0;
                    dp[0][1]=-prices[0];
                    continue;
                }
                if (i==1){
                    dp[1][0]=Math.max(dp[0][0],dp[0][1]+prices[1]);
                    dp[1][1]=Math.max(dp[0][1],-prices[1]);
                    continue;
                }
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
            }
            return dp[n-1][0];
        }

        public int maxProfitI(int[] prices) {
            int n = prices.length;

            int i_0=0,i_1=Integer.MIN_VALUE;
            int i_2_0=0;
            for (int i = 0; i < n; i++) {
                int tmp=i_0;
                i_0=Math.max(i_0,i_1+prices[i]);
                i_1=Math.max(i_1,i_2_0-prices[i]);
                i_2_0=tmp;
            }
            return i_0;
        }
    }
}
