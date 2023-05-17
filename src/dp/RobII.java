package dp;

import java.util.Arrays;

/**
 * 213. 打家劫舍 II[medium]
 * https://leetcode.cn/problems/house-robber-ii/
 */
public class RobII {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        SolutionI solution = new SolutionI();
        System.out.println(solution.rob(nums));
    }

    static class Solution {
        public int rob(int[] nums) {
            if (nums.length==1) return nums[0];

            int[] dp1=new int[nums.length];
            int[] dp2=new int[nums.length];
            Arrays.fill(dp1,-1);
            Arrays.fill(dp2,-1);

            // 对于第i个房屋
            // 抢：dp(i)=nums[i]+dp(i+2)  只能从下下家开始
            // 不抢： dp(i)=dp(i+1) 从下家开始

            //0和nums.length-1位置的房子不能同时被抢
            // 分开考虑， 0--nums.length-2  1-nums.length-1
            return Math.max(dp(nums,dp1,0,nums.length-2),dp(nums,dp2,1,nums.length-1));

        }

        private int dp(int[] nums,int[] dp, int start,int end) {
            if (start>end) return 0;
            if (dp[start]!=-1) return dp[start];
            //base case
            if (start==end){
                dp[end]=nums[end];
                return nums[end];
            }
            int rob=nums[start]+dp(nums,dp,start+2,end);
            int noRob=dp(nums,dp,start+1,end);
            int res = Math.max(rob, noRob);
            dp[start]=res;
            return res;
        }
    }

    static class SolutionI {
        public int rob(int[] nums) {
            if (nums.length==1) return nums[0];

            // 对于第i个房屋
            // 抢：dp(i)=nums[i]+dp(i+2)  只能从下下家开始
            // 不抢： dp(i)=dp(i+1) 从下家开始

            //0和nums.length-1位置的房子不能同时被抢
            // 分开考虑， 0--nums.length-2  1-nums.length-1
            return Math.max(dp(Arrays.copyOfRange(nums,0,nums.length-1)),dp(Arrays.copyOfRange(nums,1,nums.length)));

        }

        private int dp(int[] nums) {
            if (nums.length==0) return 0;
            if (nums.length==1) return nums[0];


            int[] dp=new int[nums.length];
            Arrays.fill(dp,-1);
            dp[0]=nums[0];
            dp[1]=Math.max(nums[0],nums[1]);
            for (int i = 2; i < nums.length; i++) {
                //思考第 i 个房子是否应该抢，如果要抢，那么第 i - 1 个房子就不能抢，我们只能考虑抢第 i - 2 个房子。
                //如果不抢，那么就可以抢第 i - 1 个房子，
                //如果从左到右去抢房子，抢到当前房子可以获得的最大值其实是和抢到前两个房子可以获得的最大值有关，
                dp[i]= Math.max(nums[i]+dp[i-2],dp[i-1]);
            }

            return dp[nums.length-1];
        }
    }
}
