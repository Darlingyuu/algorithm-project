package daily;

import java.util.LinkedList;

/**
 * 1091. 二进制矩阵中的最短路径[medium]
 * https://leetcode.cn/problems/shortest-path-in-binary-matrix/
 */
public class ShortestPathBinaryMatrix {
    public static void main(String[] args) {
        int[][] grid={{0,0,0,0,0,0,0,0},
                      {0,0,1,0,0,0,0,1},
                      {1,0,0,0,0,0,0,0},
                      {0,0,0,0,0,1,1,0},
                      {0,0,1,0,1,0,1,1},
                      {0,0,0,0,0,0,0,0},
                      {0,0,0,1,1,1,0,0},
                      {1,0,1,1,1,0,0,0}};
        Solution solution = new Solution();
        System.out.println(solution.shortestPathBinaryMatrix(grid));
    }

    static class Solution {
        public int shortestPathBinaryMatrix(int[][] grid) {
            int n = grid.length;
            if (grid[0][0]==1 || grid[n-1][n-1]==1){
                return -1;
            }
            // 记录每个节点到起点的最短畅通路径
            int[][] dist =new int[n][n];
            // 初始长度
            dist[0][0]=1;
            // 广度优先搜索bfs
            LinkedList<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0,0});
            //队首坐标
            int[] start;
            while (!queue.isEmpty()){
                // 弹出队首
                start = queue.poll();
                int x=start[0];
                int y=start[1];
                // 枚举八个方向
                for (int i = -1; i <=1 ; i++) {
                    for (int j = -1; j <=1 ; j++) {
                        if ((i==0 && j==0) || // 跳过本身
                            x+i<0 || x+i>=n ||  // 跳过横坐标越界
                            y+j<0 || y+j >=n || // 跳过纵坐标越界
                            grid[x+i][y+j]==1 || //跳过等于1的点
                            dist[x+i][y+j]>0 ){ // 跳过已经遍历过的点
                            continue;
                        }

                        // 路径长度+1
                        dist[x+i][y+j]=dist[x][y]+1;
                        // 新坐标入队
                        queue.offer(new int[]{x+i,y+j});
                    }
                }
            }
            // 返回终点的路径 dist[n-1][n-1]==0说明不可达
            return dist[n-1][n-1]==0?-1:dist[n-1][n-1];
        }
    }
}
