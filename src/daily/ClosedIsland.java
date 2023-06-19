package daily;

import java.util.ArrayDeque;

/**
 * 1254. 统计封闭岛屿的数目[medium]
 * https://leetcode.cn/problems/number-of-closed-islands/
 */
public class ClosedIsland {

    class Solution {
        public int closedIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int ans=0;

            int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j]==0){ // 遇到岛屿
                        ArrayDeque<int[]> queue = new ArrayDeque<>();
                        // 标记为水 1，避免走回头路
                        grid[i][j]=1;
                        // 一开始设定该区域为封闭的
                        boolean closed=true;

                        // 加入队列
                        queue.offerFirst(new int[]{i,j});

                        // 开始bfs循环
                        while (!queue.isEmpty()){
                            int[] arr = queue.poll();
                            int cx=arr[0],cy=arr[1];
                            // 若是边界位置，则不是封闭的
                            if (cx==0 || cy==0 || cx==m-1 || cy==n-1){
                                closed=false;
                            }
                            // 遍历上下左右四个方向
                            for (int d = 0; d < 4; d++) {
                                int nx = cx + dir[d][0];
                                int ny = cy + dir[d][1];
                                if (nx>=0 && nx <m && ny>=0 && ny<n && grid[nx][ny]==0){
                                    // 同样是0 岛屿
                                    // 加入队列
                                    grid[nx][ny]=1;
                                    queue.offer(new int[]{nx,ny});
                                }
                            }
                        }
                        // 该区域遍历完毕,不存在0
                        if (closed){
                            ans++;
                        }
                    }
                }
            }
            return ans;
        }


        public int closedIslandI(int[][] grid) {
            int ans = 0;
            int m = grid.length;
            int n = grid[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0 && dfs(i, j, grid, m, n)) {
                        ans++;
                    }
                }
            }
            return ans;
        }

        // 是否为封闭岛屿
        public boolean dfs(int x, int y, int[][] grid, int m, int n) {
            if (x < 0 || y < 0 || x >= m || y >= n) {
                return false;
            }
            if (grid[x][y] != 0) {
                return true;
            }
            grid[x][y] = -1;
            boolean ret1 = dfs(x - 1, y, grid, m, n);
            boolean ret2 = dfs(x + 1, y, grid, m, n);
            boolean ret3 = dfs(x, y - 1, grid, m, n);
            boolean ret4 = dfs(x, y + 1, grid, m, n);
            return ret1 && ret2 && ret3 && ret4;
        }
    }
}
