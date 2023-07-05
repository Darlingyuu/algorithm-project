package dp;

import java.util.Arrays;

/**
 * 45. 跳跃游戏 II[medium]
 * https://leetcode.cn/problems/jump-game-ii/
 */
public class Jump {
    class Solution {
        public int jump(int[] nums) {
            int n = nums.length;
            // dp[i]是到达nums[i]的最小跳跃次数
            int[] dp = new int[n];
            Arrays.fill(dp,Integer.MAX_VALUE/2);
            dp[0]=0;
            for (int i = 0; i < n; i++) {
                // i位置可以到达的位置[i+1,i+nums[i]]
                for (int j = i+1; j <=i+nums[i] && j<n; j++) {
                    dp[j]=Math.min(dp[j],dp[i]+1);
                }
            }
            return dp[n-1];
        }

        public int jumpII(int[] nums) {
            int n = nums.length;
            // 上次跳跃可达范围右边界（下次的最右起跳点）
            int end=0;
            // 记录能够到达的最后面下标
            int maxPosition=0;
            // 记录最小跳跃步数
            int minPath=0;
            for (int i = 0; i < n-1; i++) {
                maxPosition = Math.max(maxPosition, i + nums[i]);
                // 到达上次跳跃能到达的右边界了
                if (i==end){
                    // 目前能跳到的最远位置变成了下次起跳位置的有边界
                    end= maxPosition;
                    // 进入下一次跳跃
                    minPath++;
                }
            }
            return minPath;
        }
    }
}
