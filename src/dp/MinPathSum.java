package dp;

import java.util.Arrays;

/**
 * 64. 最小路径和[medium]
 * https://leetcode.cn/problems/minimum-path-sum/
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid={{1,3,1},{1,5,1},{4,2,1}};
        Solution solution = new Solution();
        System.out.println(solution.minPathSum(grid));
    }
    static class Solution {
        int[][] memo;
        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            // memo[i][j]记录[0,0]位置到达[i,j]位置的最小路径和
            memo = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(grid,m-1,n-1);
        }

        // dp(grid,i,j)返回[0,0]位置到达[i,j]位置的最小路径和
        private int dp(int[][] grid, int i, int j) {
            // base case
            if (i == 0 && j == 0) {
                return grid[0][0];
            }
            // 如果索引出界，返回一个很大的值，
            // 保证在取 min 的时候不会被取到
            if (i<0 || j<0 ){
                return Integer.MAX_VALUE;
            }
            if (memo[i][j]!=-1) return memo[i][j];

            // 到达[i,j]只能从[i,j-1]或[i-1,j]到达
            memo[i][j]=Math.min(dp(grid,i,j-1),dp(grid,i-1,j))+grid[i][j];
            return memo[i][j];
        }
    }
}
