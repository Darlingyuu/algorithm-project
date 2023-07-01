package dp;

/**
 * 486. 预测赢家[medium]
 * https://leetcode.cn/problems/predict-the-winner/
 */
public class PredictTheWinner {
    class Solution {
        public boolean PredictTheWinner(int[] nums) {
            /**
             * 状态有三个：开始的索引 i，结束的索引 j，当前轮到的人。
             * 选择有两个：选择最左边的那堆石头，或者选择最右边的那堆石头
             */
            int n = nums.length;
            // dp[i][j][0]表示在nums[i,j]范围先手能得到的最高分
            // dp[i][j][1]表示在nums[i,j]范围后手得到的最高分
            int[][][] dp = new int[n][n][2];
            // base case
            for (int i = 0; i < n; i++) {
                // 每个nums[i,i]区间，先手有分后手无分
                dp[i][i][0]=nums[i];
                dp[i][i][1]=0;
            }

            // 穷举所有状态
            // 倒着遍历数组  [n-2,n-1]
            for (int i = n-2; i >=0; i--) {
                for (int j = i+1; j < n; j++) {
                    // nums[i,j]
                    // 先手选择最左边或最右边的分数
                    // 本次选择最左边，下一次范围为[i+1,j]且下一次作为后手
                    int left = nums[i] + dp[i + 1][j][1];
                    //  本次选择最右边，下一次范围为[i,j-1]且下一次作为后手
                    int right = nums[j] + dp[i][j - 1][1];

                    // 先手肯定会选择更大的结果，后手的选择随之改变
                    if (left>right){ // 先手选左边
                        dp[i][j][0]=left;
                        // 后手就是下一次范围[i+1,j]的先手
                        dp[i][j][1]=dp[i+1][j][0];
                    }else { // 先手选右边
                        dp[i][j][0]=right;
                        // 后手就是下一次范围[i,j-1]的先手
                        dp[i][j][1]=dp[i][j-1][0];

                    }
                }
            }

            // 结果 [0,n-1]范围内先手分数是否大于后手
            return dp[0][n-1][0]>=dp[0][n-1][1];
        }


    }

}
