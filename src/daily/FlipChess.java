package daily;

import java.util.LinkedList;

/**
 * LCP 41. 黑白翻转棋[medium]
 * https://leetcode.cn/problems/fHi6rV/
 */
public class FlipChess {
    class Solution {
        int m;
        int n;

        public int flipChess(String[] chessboard) {
            m = chessboard.length;
            n = chessboard[0].length();
            int ans = 0;
            //  枚举对空余位置下一个黑棋
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (chessboard[i].charAt(j) == '.') {
                        ans = Math.max(ans, bfs(chessboard, i, j));
                    }
                }
            }
            return ans;
        }


        int[][] dirs = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        /**
         * 我们对每一个空余位置尝试黑棋放置，用一个队列来存储正在进行「翻转操作」的黑棋位置
         * 若队列非空，我们从队列中取出队首元素，进行行、列和对角线8 个方向判断是否有可以翻转的白棋
         *      ——判断沿着方向是否是连续的一段白棋并以另一颗黑棋结尾。
         * 若有可以翻转的白棋，则将这些白旗进行翻转，并加入队列中。
         * 直至队列为空表示一次放置黑棋结束。
         */
        // 在(x,y)位置下黑棋，能翻转的白棋数量
        private int bfs(String[] chessboard, int x, int y) {
            // 模拟棋盘
            char[][] board = new char[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j]=chessboard[i].charAt(j);
                }
            }

            int count=0;
            // 用一个队列来存储正在进行「翻转操作」的黑棋位置
            LinkedList<int[]> queue = new LinkedList<>();
            // 当前正在进行的是（x,y）
            queue.offer(new int[]{x,y});
            // 该该点改为X
            board[x][y]='X';
            while (!queue.isEmpty()){
                int[] cur = queue.poll();
                // 八个方向
                for (int i = 0; i < 8; i++) {
                    // 判断沿着方向是否是连续的一段白棋并以另一颗黑棋结尾
                    if (judge(board, cur[0], cur[1], dirs[i][0], dirs[i][1])) {
                        // 该方向是连续白棋且以另一颗黑棋结尾
                        int dx=cur[0]+dirs[i][0];
                        int dy=cur[1]+dirs[i][1];
                        // 遍历这段棋子
                        while (board[dx][dy]!='X'){
                            // 不是黑棋X，而是白棋,则将下标加入队列，便于后续遍历其八个方向
                            queue.offer(new int[]{dx,dy});
                            // 将当前位置改为黑棋，避免重复
                            board[dx][dy]='X';
                            // 翻转数增加
                            count++;
                        }
                    }
                }
            }
            return count;
        }

        // 判断沿着方向是否是连续的一段白棋并以另一颗黑棋结尾
        private boolean judge(char[][] board, int x, int y, int dx, int dy) {
            x+=dx;
            y+=dy;
            while (0<=x && x<board.length && 0<=y && y<board[0].length){
                // 遇到黑棋
                if (board[x][y]=='X'){
                    return true;
                }else if (board[x][y] == '.') {
                    // 遇到.
                    return false;
                }
                // 遇到白棋，继续验证该方向判断
                x+=dx;
                y+=dy;
            }
            // 走到此处，说明一直是白棋到了边界，该方向上没有黑棋结尾
            return false;
        }


    }

}
