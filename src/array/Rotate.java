package array;

import java.util.Arrays;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/28 21:29
 * 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像
 */
public class Rotate {
    public static void main(String[] args) {
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        Solution solution = new Solution();
        solution.rotate(matrix);
        System.out.println("------------------------");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    static class Solution {
        public void rotate(int[][] matrix) {
            // 对角线:
            // 1 （i=j）下标变换规律：变化后(i,j)位置的数 <--- (|length-1-i|,j)
            // 2  （i+j=length-1 && i!=j）下标变换规律：变化后(i,j)位置的数 <--- (|length-1-j|,i)

            // 非对角线(i!=j)下标变换规律：(i,j) <-- (j,|length-i|)
            int length = matrix.length;
            // 用来记录原始数组
//            int[][] array = Arrays.copyOf(matrix,length);  浅拷贝不可取
            int[][] array=new int[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    array[i][j]=matrix[i][j];
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                System.out.println(Arrays.toString(array[i]));
            }
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (i==j){
                        matrix[i][j]=array[Math.abs(length-1-i)][j];
                    }else if(i+j==length-1 && i!=j){
                        matrix[i][j]=array[i][Math.abs(length-1-j)];
                    }else {
                        matrix[i][j]=array[Math.abs(length-1-j)][i];
                    }
                }
            }
        }

        /**
         * 题解版 直接交换(上述优版)
         * @param matrix
         */
        public void rotateII(int[][] matrix) {
            int length = matrix.length;
            //因为是对称的，只需要计算循环前半行即可
            for (int i = 0; i < length / 2; i++)
                for (int j = i; j < length - i - 1; j++) {
                    int temp = matrix[i][j];
                    int m = length - j - 1;
                    int n = length - i - 1;
                    matrix[i][j] = matrix[m][i];
                    matrix[m][i] = matrix[n][m];
                    matrix[n][m] = matrix[j][n];
                    matrix[j][n] = temp;
                }
        }

        /**
         * 题解版 先上下交换，在对角线交换
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
