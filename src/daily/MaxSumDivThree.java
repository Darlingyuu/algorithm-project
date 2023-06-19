package daily;

import java.util.Arrays;

/**
 * 1262. 可被三整除的最大和[medium]
 * https://leetcode.cn/problems/greatest-sum-divisible-by-three/
 */
public class MaxSumDivThree {
    class Solution {
        public int maxSumDivThree(int[] nums) {
            int n = nums.length;
            int[][] memo = new int[n][3];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dfs(memo,nums,n-1,0);
        }

        /**
         * 考虑最后一个数x=nums[n-1]
         * 1. 如果x%3==0，选x，问题变成nums[0]到nums[n-2]中寻找能被3整除的元素最大和s，答案为x+s
         * 2. 如果x%3==1
         *      a. 不选x，问题变成nums[0]到nums[n-2]中寻找能被3整除的元素最大和s0
         *      b. 选x，问题变成nums[0]到nums[n-2]中寻找元素最大和s1，且s1%3=2
         *      c. 答案为max(s0,s1+x)
         * 3. 如果x%3==2
         *      a. 不选x，问题变成nums[0]到nums[n-2]中寻找能被3整除的元素最大和s0
         *      b. 选x，问题变成nums[0]到nums[n-2]中寻找元素最大和s1，且s1%3=1
         *      c. 答案为max(s0,s1+x)
         */
        // dfs(i,j)返回 从nums[0]到nums[i]中选数，已选元素之和s，且s%3=j ,s的最大值
        private int dfs(int[][] memo, int[] nums, int i, int j) {
            if (i<0){
                return j==0 ? 0:Integer.MIN_VALUE;
            }
            // 之前计算过
            if (memo[i][j]!=-1){
                return memo[i][j];
            }

            // 不选x=nums[n-1]
            int x = dfs(memo, nums, i - 1, j);
            // 选x=nums[n-1],已选数字之和%3=(j+nums[i])%3
            int y = dfs(memo, nums, i - 1, (j + nums[i]) % 3) + nums[i];
            return memo[i][j]=Math.max(x,y);
        }

    }
}
