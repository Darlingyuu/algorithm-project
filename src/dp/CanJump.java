package dp;

/**
 * 55. 跳跃游戏[medium]
 * https://leetcode.cn/problems/jump-game/
 */
public class CanJump {
    class Solution {

        public boolean canJump(int[] nums) {
            int n = nums.length;
            // dp[i]表示是否可以到达nums[i]
            boolean[] dp = new boolean[n];
            dp[0]=true;
            for (int i = 0; i < n; i++) {
                // i位置，[i+1,i+nums[i]]均可以到达
                if (dp[i]) {
                    for (int j = i + 1; j <=i+nums[i] && j<n; j++) {
                        dp[j]=true;
                    }
                }
            }
            return dp[n-1];
        }

        public boolean canJumpII(int[] nums) {
            int n = nums.length;
            // 直接记录能够到达的最远位置，那小于该位置的肯定能到达
            int rightmost=0;
            for (int i = 0; i < n; i++) {
                if (i<=rightmost){
                    rightmost=Math.max(rightmost,i+nums[i]);
                    // 判断是否可以到达终点
                    if (rightmost>=n-1) return true;
                }
            }
            return false;
        }
    }
}
