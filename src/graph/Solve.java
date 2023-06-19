package graph;

import java.util.Arrays;

/**
 * 130. 被围绕的区域[medium]
 * https://leetcode.cn/problems/surrounded-regions/
 */
public class Solve {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board={{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solution.solve(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    static class Solution {
        int m;
        int n ;
        public void solve(char[][] board) {
            m = board.length;
            n = board[0].length;
            boolean[][] visit = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 处理和边界上的O连通的位置
                    if ((i==0 || i==m-1 || j==0 || j==n-1) && board[i][j]=='O'){
                        dfs(board,i,j);
                    }

                }
            }

            // 其他位置的O必定是被X包围的，将其改成X即可
            // 同时将之前与边界连通部分#改回O
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 先改O为X
                    if (board[i][j]=='O'){
                        board[i][j]='X';
                    }
                    if (board[i][j]=='#'){
                        board[i][j]='O';
                    }
                }
            }

        }


        // 将i,j及连通区域的O全部标记为#
        private void dfs(char[][] board, int i, int j) {
            if (i < 0 || j < 0 || i >= board.length  || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
                // board[i][j] == '#' 说明已经搜索过了.
                return;
            }

            board[i][j]='#';

            // 上
            dfs(board, i - 1, j);
            // 下
            dfs(board, i + 1, j);
            // 左
            dfs(board, i, j-1);
            // 右
            dfs(board, i, j+1);
        }
    }
}
