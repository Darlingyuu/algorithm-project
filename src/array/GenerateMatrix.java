package array;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II[medium]
 * https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class GenerateMatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ints = solution.generateMatrix(3);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    static class Solution {
        public int[][] generateMatrix(int n) {
            int[][] res = new int[n][n];
            // 上边界
            int top=0;
            // 右边界
            int right=n-1;
            // 下边界
            int down=n-1;
            // 左边界
            int left=0;
            int count=1;
            while (count<=n*n){
                if (top<=down) {
                    // 上 从左往右
                    for (int i = left; i <= right; i++) {
                        res[top][i] = count++;
                    }
                    top++;
                }

                if (left<=right){
                    // 右侧 从上向下
                    for (int i = top; i <=down ; i++) {
                        res[i][right]=count++;
                    }
                    right--;
                }

                if (top<=down){
                    // 底部 从右向左
                    for (int i = right; i >=left ; i--) {
                        res[down][i]=count++;
                    }
                    down--;
                }

                if (left<=right){
                    // 左侧 从下向上
                    for (int i = down; i >=top ; i--) {
                        res[i][left]=count++;
                    }
                    left++;
                }
            }
            return res;
        }
    }
}
