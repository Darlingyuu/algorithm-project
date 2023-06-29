package dp;

import java.util.Arrays;

/**
 * 174. 地下城游戏[hard]
 * https://leetcode.cn/problems/dungeon-game/
 */
public class CalculateMinimumHP {
    class Solution {
        int[][] memo;
        int m;
        int n;
        public int calculateMinimumHP(int[][] dungeon) {
            m = dungeon.length;
            n = dungeon[0].length;
            // memo[i][j]记录从[i,j]到[m-1][n-1]需要的最小血量
            memo = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(dungeon,0,0);
        }

        /**
         * 此处定义dp(dungeon,i,j)返回从[i,j]到[m-1][n-1]需要的最小血量
         * 而不是返回从[0,0]到[i,j]需要的最小血量
         * 因为从[0,0]到[i,j]，无法获取之前状态的血量，从而无法从[i-1,j]和[i,j-1]推出[i,j]状态
         * 所以逆序，先保证能到达[m-1][n-1]，[i,j]可以从[i,j+1]和[i+1,j]的状态得到
         */

        // dp(dungeon,i,j)返回从[i,j]到[m-1][n-1]需要的最小血量
        private int dp(int[][] dungeon, int i, int j) {
            // base case
            if (i==m-1 && j==n-1){
                // 保证到达终终点，最起码有一个血量
                return memo[i][j]=dungeon[i][j]>0?1:-dungeon[i][j]+1;
            }

            if (i==m || j==n){
                return Integer.MAX_VALUE;
            }

            if (memo[i][j]!=-1) return memo[i][j];

            // [i.j]向[i,j+1],[i+1,j]走
            // [i,j]补充dungeon[i][j]血量 接下来至少min(),当前最少min-dungeon[i][j]
            int res = Math.min(dp(dungeon, i, j + 1), dp(dungeon, i + 1, j)) - dungeon[i][j];
            // 保证在[i,j]位置不死
            memo[i][j]=res<=0?1:res;
            return memo[i][j];
        }
    }
}
