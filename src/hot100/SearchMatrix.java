package hot100;

/**
 * 240. 搜索二维矩阵 II[medium]
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/?envType=study-plan-v2&envId=top-100-liked
 */
public class SearchMatrix {
    class Solution {
        // Z 字形查找
        public boolean searchMatrixIII(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            // 从右上角开始
            // 搜索矩阵：以 matrix的左下角为左下角、以 (x,y)为右上角的矩阵中进行搜索
            int x = 0, y = n - 1;
            while (x<m && y>=0){
                if (matrix[x][y] == target) {
                    return true;
                }
                // 由于每一列的元素都是升序排列的
                // 那么在当前的搜索矩阵中，所有位于第 y列的元素都是严格大于 target的，因此我们可以将它们全部忽略
                // 即将 yy减少 1
                if (matrix[x][y] > target) {
                    --y;
                } else {
                    // 由于每一行的元素都是升序排列的，
                    // 那么在当前的搜索矩阵中，所有位于第 x行的元素都是严格小于 target，因此我们可以将它们全部忽略
                    // 即将 x增加 1
                    ++x;
                }
            }
            return false;
        }

        // 对每一行二分查找
        public boolean searchMatrixII(int[][] matrix, int target) {
            for (int[] row : matrix) {
                int index = search(row, target);
                if (index >= 0) {
                    return true;
                }
            }
            return false;
        }
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int num = nums[mid];
                if (num == target) {
                    return mid;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
        }


        // 直接查找
        public boolean searchMatrixI(int[][] matrix, int target) {
            for (int[] row : matrix) {
                for (int element : row) {
                    if (element == target) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[][] isVisited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isVisited[i][j]) continue;
                    boolean flag = searchMatrix(matrix, i, j, target, isVisited);
                    if (flag) {
                        return flag;
                    }
                }
            }
            return false;
        }

        // 以[i][j]为起点是否找到target
        private boolean searchMatrix(int[][] matrix, int i, int j, int target, boolean[][] isVisited) {
            if (target == matrix[i][j]) {
                return true;
            }

            boolean res = false;

            // 当前数
            int num = matrix[i][j];
            isVisited[i][j] = true;
            if (num < target) {
                // 向下或向右
                if (i < matrix.length - 1 && !isVisited[i + 1][j]) {
                    res |= searchMatrix(matrix, i + 1, j, target, isVisited);
                }
                if (j < matrix[0].length - 1 && !isVisited[i][j + 1]) {
                    res |= searchMatrix(matrix, i, j + 1, target, isVisited);
                }
                if (res) {
                    return true;
                }
            }

            if (num > target) {
                // 向上或向左
                if (i > 0 && !isVisited[i - 1][j]) {
                    res |= searchMatrix(matrix, i - 1, j, target, isVisited);
                }
                if (j > 0 && !isVisited[i][j - 1]) {
                    res |= searchMatrix(matrix, i, j - 1, target, isVisited);
                }
                if (res) {
                    return true;
                }
            }
            return false;
        }
    }
}
