package hot100;

/**
 * 48. 旋转图像[medium]
 * https://leetcode.cn/problems/rotate-image/?envType=study-plan-v2&envId=top-100-liked
 */
public class RotateII {
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // 先上下翻转
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n; j++) {
                    int[] temp = matrix[i];
                    matrix[i]=matrix[n-1-i];
                    matrix[n-1-i]=temp;
                }
            }

            // 沿对角线\ 交换
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j]=matrix[j][i];
                    matrix[j][i]=temp;
                }
            }
        }
    }
}
