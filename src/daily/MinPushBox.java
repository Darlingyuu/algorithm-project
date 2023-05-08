package daily;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 1263. 推箱子[hard]
 * 20230508
 */
public class MinPushBox {
    public static void main(String[] args) {
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}
        };
        Solution solution = new Solution();
        int i = solution.minPushBox(grid);
        System.out.println(i); //3
    }

    /**
     * 时间复杂度 O(m2*n2)
     * 空间复杂度 O(m2*n2)
     */
    static class Solution {
        private int m;// 网格行数
        private int n;// 网格列数
        private char[][] grid;

        /*
           把玩家的位置和箱子的位置看成一个状态，即(si,sj,bi,bj)
           在代码实现上，我们定义一个函数 f(i,j),它将二维坐标 (i,j)映射到一个一维的状态编号，即 f(i,j)=i*n+j
         */
        private int f(int i, int j) {
            return i * n + j;//n为网格列数
        }

        // 判断位置是否合法
        private boolean check(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != '#';
        }

        public int minPushBox(char[][] grid) {
            // 记录网格长宽
            m = grid.length;
            n = grid[0].length;
            this.grid = grid;
            // 玩家、箱子的初始位置
            int si = -1, sj = -1, bi = -1, bj = -1;
            // 遍历网格，找到玩家、箱子的实际位置
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == 'S') {
                        si = x;
                        sj = y;
                    } else if (grid[x][y] == 'B') {
                        bi = x;
                        bj = y;
                    }
                }
            }


            int[][] dp = new int[m * n][m * n];
            for (int i = 0; i < m * n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            /*
                定义一个双端队列q，其中每个元素都是一个三元组(f(si,sj),f(bi,bj),d)
                表示玩家位于(si,sj),箱子位于(bi,bj)，并且已经进行了d次推动
                初始时，将(f(si,sj),f(bi,bj),0)加入q
             */
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{f(si, sj), f(bi, bj), 0});

            /*
                定义一个二维数组vis记录每个状态是否已经访问过
                初始时vis[f(si,sj),f(bi,bj)]标记为已访问

                网格最大下标[m-1,n-1] f(m-1,n-1)=m*n-1，定义vis长宽为m*n
             */
            boolean[][] vis = new boolean[m * n][m * n];
            vis[f(si, sj)][f(bi, bj)] = true;

            /*
                进行广度优先搜索
                1. 每一步搜索，我们取出队头元素(f(si,sj),f(bi,bj),d)，并且检查是否满足grid[bi][bj]='T'
                   如果是，说明箱子已经被推到目标位置，此时将d作为答案返回即可
                2. 否则，我们将枚举玩家的下一步移动方向，玩家新的位置记为(sx,sy)，
                   如果(sx,sy)是一个合法的位置，我们判断此时(sx,sy)是否与箱子的位置(bi,bj)相同：
                   a. 如果相同，说明当前玩家到达了箱子的位置，并且推动箱子往前走了一步。箱子的新位置为(bx,by),
                      如果(bx,by)是一个合法的位置，且状态 vis[f(sx,sy)][f(bx,by)]没有被访问过，
                      那么我们就将(f(sx,sy)][f(bx,by),d+1)加入队列q的末尾，并将vis[f(sx,sy)][f(bx,by)]标记为已访问
                   b. 如果不同，说明当前玩家没有推动箱子，那么我们只需要判断状态vis[f(sx,sy)][f(bi,bj)]是否被访问过，
                      如果没有被访问过，我们就将((sx,sy)][f(bi,bj),d)加入队列q的头部，并将vis[f(sx,sy)][f(bi,bj)]标记为已访问
                继续进行广度优先搜索，知道队列为空为止

                注意，如果推动箱子，那么推动次数 d 需要加 1，并且新的状态加入到队列 q 的末尾；
                如果没推动箱子，那么推动次数 d 不变，新的状态加入到队列 q 的头部
                最后，如果没有找到合法的推动方案，那么返回 −1。
             */

            // 用来帮助得到上下左右的位置
            int[] dir = {0, -1, 0, 1, 0};

            while (!q.isEmpty()) {
                // 取出队列头部元素
                int[] p = q.poll();
                // 当前位置的推动箱子次数
                int d = p[2];
                // 当前位置箱子的坐标
                bi = p[1] / n;
                bj = p[1] % n;
                // 判断箱子是否被推至目标位置
                if (grid[bi][bj] == 'T') {
                    return d;
                }
                // 未到达目标位置

                //当前位置人的坐标
                si = p[0] / n;
                sj = p[0] % n;

                for (int k = 0; k < 4; k++) {
                    /*
                       得到上下左右位置
                       k=0  i,j-1  左
                       k=1  i+1,j  上
                       k=2  i,j+1  下
                       k=3  i+1,j  右
                     */
                    int sx = si + dir[k], sy = sj + dir[k + 1];
                    // 检查(sx,sy)是否一个合法的位置
                    if (!check(sx, sy)) {
                        // 不合法，继续判断下一个位置
                        continue;
                    }
                    // 位置合法

                    // 判断当前位置是否与箱子的位置(bi,bj)相同：
                    // 如果相同，说明当前玩家到达了箱子的位置，并且推动箱子往前走了一步。箱子的新位置为(bx,by)
                    if (sx == bi && sy == bj) {
                        int bx = bi + dir[k], by = bj + dir[k + 1];
                        // 判断箱子位置是否合法，并且vis[f(sx,sy)][f(bx,by)]是否被访问过
                        if (!check(bx, by) || vis[f(sx, sy)][f(bx, by)]) {
                            // 不合法或者被访问过，继续判断下一个位置
                            continue;
                        }

                        //位置合法并且没有被访问过
                        //将(f(sx,sy)][f(bx,by),d+1)加入队列q的末尾，并将vis[f(sx,sy)][f(bx,by)]标记为已访问
                        vis[f(sx, sy)][f(bx, by)] = true;
                        q.offer(new int[]{f(sx, sy), f(bx, by), d + 1});
                    } else if (!vis[f(sx, sy)][f(bi, bj)]) {
                        // 没有到达箱子位置，说明未推动,!vis[f(sx,sy)][f(bi,bj)说明该位置未访问过
                        // 将((sx,sy)][f(bi,bj),d)加入队列q的头部，并将vis[f(sx,sy)][f(bi,bj)]标记为已访问
                        vis[f(sx, sy)][f(bi, bj)] = true;
                        q.offerFirst(new int[]{f(sx, sy), f(bi, bj), d});
                    }
                }
            }
            return -1;

        }

    }

}
