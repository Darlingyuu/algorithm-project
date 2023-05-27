package array;

import java.util.Arrays;

/**
 * 48. 旋转图像[medium]
 * https://leetcode.cn/problems/rotate-image/
 * 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像
 */
public class Rotate {
    public static void main(String[] args) {
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        Solution solution = new Solution();
        solution.rotateIIII(matrix);
        System.out.println("------------------------");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    static class Solution {
        public void rotateIIII(int[][] matrix) {
            int length = matrix.length;
            // 上下交换
            for (int i = 0; i < length/2; i++) {
                int[] temp = matrix[i];
                matrix[i]=matrix[length-1-i];
                matrix[length-1-i]=temp;
            }
            // 沿对角线\ 交换
            for (int i = 0; i < length; i++) {
                for (int j = i; j < length; j++) {
                    int temp=matrix[i][j];
                    matrix[i][j]=matrix[j][i];
                    matrix[j][i]=temp;
                }
            }
        }

        /**
         * 题解版 先上下交换,再对角线交换
         * @param matrix
         */
        public void rotateIII(int[][] matrix) {
            int length = matrix.length;
            //先上下交换
            for (int i = 0; i < length / 2; i++) {
                int temp[] = matrix[i];
                matrix[i] = matrix[length - i - 1];
                matrix[length - i - 1] = temp;
            }
            //在按照对角线交换
            for (int i = 0; i < length; ++i) {
                for (int j = i + 1; j < length; ++j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }
}
