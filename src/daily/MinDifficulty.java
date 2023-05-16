package daily;

import java.util.Arrays;

/**
 * 1335. 工作计划的最低难度[hard]
 * https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/
 */
public class MinDifficulty {
    public static void main(String[] args) {
        int[] jobDifficulty={2,5,4,3,5,6};
        Solution solution = new Solution();
        System.out.println(solution.minDifficulty(jobDifficulty, 3));
        for (int[] ints : solution.dp) {
            System.out.println(Arrays.toString(ints));
        }
    }

    static class Solution {
        private int[] jobDifficulty;
        private int[][] dp;
        public int minDifficulty(int[] jobDifficulty, int d) {
            //任务数
            int n = jobDifficulty.length;
            // 任务数过少，无法分配到每天
            if (n<d) return -1;
            this.jobDifficulty=jobDifficulty;
            dp=new int[d][n];
            for (int i = 0; i < d; i++) {
                Arrays.fill(dp[i],-1);//表示未计算过
            }
            return dfs(d-1,n-1);
        }

        //天数为i+1,任务数为j+1时，最小难度和为dfs(i,j)
        //下标从0开始
        private int dfs(int i, int j) {
            if (dp[i][j]!=-1){
                //之前计算过，直接返回即可
                return dp[i][j];
            }
            if (i==0){
                //只有一天，需完成所有
                int mx=0;
                for (int k = 0; k <= j; k++) {
                    mx=Math.max(jobDifficulty[k],mx);
                }
                return dp[i][j]=mx;
            }

            int res=Integer.MAX_VALUE;
            int mx=0;
            //遍历任务
            for (int k = j; k >=i; k--) {
                //i+1天内完成k+1个任务，k>=i
                // jobDifficulty[k]到jobDifficulty[j]任务难度的最大值
                mx=Math.max(jobDifficulty[k],mx);
                //最后一天(第i+1天)完成最后的第k+1到j+1的任务，则第i+1天的难度为mx
                //第0到第i天需完成第0到第k的任务,，最小难度和为dfs(i-1,k-1)
                // dfs(i,j)=dfs(i-1,k-1)+mx
                res=Math.min(res,dfs(i-1,k-1)+mx);
            }
            return dp[i][j]=res;
        }


    }

}
