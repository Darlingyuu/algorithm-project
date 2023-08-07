package hot100;

/**
 * 73. 矩阵置零[medium]
 * https://leetcode.cn/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-100-liked
 */
public class SetZeroes {
    class Solution {
        // 额外使用O(m+n)的空间
        public void setZeroesI(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            // 用两个标记数组分别记录每一行和每一列是否有零出现
            boolean[] row = new boolean[m];
            boolean[] col = new boolean[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        row[i] = col[j] = true;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (row[i] || col[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }


        // 额外使用O(m*n)的空间
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    temp[i][j] = matrix[i][j];
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        // 所在行列均改为0
                        for (int column = 0; column < n; column++) {
                            temp[i][column] = 0;
                        }
                        for (int row = 0; row < m; row++) {
                            temp[row][j] = 0;
                        }
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = temp[i][j];
                }
            }
        }
    }
}
