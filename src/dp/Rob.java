package dp;

import java.util.Arrays;

/**
 * 198. 打家劫舍[medium]
 * https://leetcode.cn/problems/house-robber/
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums={1,2,3,1};
        SolutionI solution = new SolutionI();
        System.out.println(solution.rob(nums));
    }

    static class Solution {
        int[] dp;
        public int rob(int[] nums) {
            dp=new int[nums.length];
            Arrays.fill(dp,-1);

            // 对于第i个房屋
            // 抢：dp(i)=nums[i]+dp(i+2)  只能从下下家开始
            // 不抢： dp(i)=dp(i+1) 从下家开始

            return dp(nums,0);
        }

        private int dp(int[] nums, int i) {
            if (i>=nums.length) return 0;
            if (dp[i]!=-1) return dp[i];
            //base case
            if (i==nums.length-1){
                dp[nums.length-1]=nums[nums.length-1];
                return nums[nums.length-1];
            }


            int rob=nums[i]+dp(nums,i+2);
            int noRob=dp(nums,i+1);
            int res = Math.max(rob, noRob);
            dp[i]=res;
            return res;
        }
    }

    static class SolutionI {
        public int rob(int[] nums) {
            if (nums.length==1) return nums[0];
            int[] dp=new int[nums.length];
            Arrays.fill(dp,-1);

            // dp[i]记录抢到第i家时最大金额
            // 抢：dp[i]=nums[i]+dp(i+2)  只能从下下家开始
            // 不抢： dp(i)=dp(i+1) 从下家开始
            dp[0]=nums[0];
            dp[1]=Math.max(nums[0],nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i]=Math.max(nums[i]+dp[i-2],dp[i-1]);
            }
            return dp[nums.length-1];
        }
    }
}
