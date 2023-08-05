package daily;

/**
 * 980. 不同路径 III[hard]
 * https://leetcode.cn/problems/unique-paths-iii/
 */
public class UniquePathsIII {
    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        Solution solution = new Solution();
        System.out.println(solution.uniquePathsIII(grid));
    }

    static class Solution {
        public int uniquePathsIII(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            // 记录无障碍方格
            int count = 0;
            // 起点
            int startX = 0;
            int startY = 0;
            // 终点
            int endX = 0;
            int endY = 0;

            // 找到起点，终点.无障碍方格数
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int g = grid[i][j];
                    if (g == 1) {
                        startX = i;
                        startY = j;
                    }
                    if (g == 2) {
                        endX = i;
                        endY = j;
                    }
                    if (g == 0) {
                        count++;
                    }
                }
            }

            // isVisited[i][j]  false表示未走过
            boolean[][] isVisited = new boolean[m][n];

            // 标记起点被走过
            isVisited[startX][startY] = true;
            // 从起点出发
            int path = uniquePaths(grid, startX, startY, endX, endY, count, isVisited);
            return path == -1 ? 0 : path;
        }

        // 从起始方格到结束方格的不同路径的数目
        private int uniquePaths(int[][] grid, int startX, int startY, int endX, int endY, int count, boolean[][] isVisited) {

            // 每个无障碍方格都经过了
            if (count == 0) {
                // 该路径可以到达终点
                if ((startX - 1 == endX && startY == endY) || (startX + 1 == endX && startY == endY)
                        || (startX == endX && startY - 1 == endY) || (startX == endX && startY + 1 == endY)) {
                    return 1;
                }
                return -1;
            }

            int paths = 0;

            // 为0才能走
            // 以起点上方的格子重新作为起点的路径个数
            if (startX > 0 && grid[startX - 1][startY] == 0 && !isVisited[startX - 1][startY]) {
                isVisited[startX - 1][startY] = true;
                // 选择该路径
                int ps = uniquePaths(grid, startX - 1, startY, endX, endY, count - 1, isVisited);
                if (ps != -1) {
                    paths += ps;
                }
                // 撤回
                isVisited[startX - 1][startY] = false;
            }

            // 以起点下方的格子重新作为起点的路径个数
            if (startX < grid.length - 1 && grid[startX + 1][startY] == 0 && !isVisited[startX + 1][startY]) {
                isVisited[startX + 1][startY] = true;
                // 选择该路径
                int ps = uniquePaths(grid, startX + 1, startY, endX, endY, count - 1, isVisited);
                if (ps != -1) {
                    paths += ps;
                }
                // 撤回
                isVisited[startX + 1][startY] = false;
            }

            // 以起点左方的格子重新作为起点的路径个数
            if (startY > 0 && grid[startX][startY - 1] == 0 && !isVisited[startX][startY - 1]) {
                isVisited[startX][startY - 1] = true;
                // 选择该路径
                int ps = uniquePaths(grid, startX, startY - 1, endX, endY, count - 1, isVisited);
                if (ps != -1) {
                    paths += ps;
                }
                // 撤回
                isVisited[startX][startY - 1] = false;
            }

            // 以起点右方的格子重新作为起点的路径个数
            if (startY < grid[0].length - 1 && grid[startX][startY + 1] == 0 && !isVisited[startX][startY + 1]) {
                isVisited[startX][startY + 1] = true;
                // 选择该路径
                int ps = uniquePaths(grid, startX, startY + 1, endX, endY, count - 1, isVisited);
                if (ps != -1) {
                    paths += ps;
                }
                // 撤回
                isVisited[startX][startY + 1] = false;
            }

            return paths;
        }
    }
}
