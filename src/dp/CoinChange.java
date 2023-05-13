package dp;

import java.util.Arrays;

/**
 * 322. 零钱兑换[medium]
 * https://leetcode.cn/problems/coin-change/
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins={1,2,5};
        int amount=11;
        Solution solution = new Solution();
        System.out.println(solution.coinChange(coins, amount));
    }

    static class Solution {
        int[] dp;
        public int coinChange(int[] coins, int amount) {
            dp=new int[amount+1];
            Arrays.fill(dp,-11);
            return dp(coins, amount);
        }

        // 凑成总数额amount，需要硬币数dp[amount]
        private int dp(int[] coins, int amount){
            // base case
            if (amount==0) return 0;
            if (amount<0) return -1;
            //查dp数组，防止重复计算
            if (dp[amount]!=-11) return dp[amount];

            int res=Integer.MAX_VALUE;
            for (int coin : coins) {
                int subProblem = dp(coins, amount - coin);
                //子问题无解
                if (subProblem==-1){
                    continue;
                }else {
                    //子问题有解，选择最优解
                    res=Math.min(res,subProblem+1);
                }
            }
            //把结果存入dp数组中
            dp[amount]=(res==Integer.MAX_VALUE)?-1:res;
            return dp[amount];
        }
    }
}
