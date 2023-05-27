package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵[medium]
 * https://leetcode.cn/problems/spiral-matrix/
 */
public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        Solution solution = new Solution();
        List<Integer> list = solution.spiralOrder(matrix);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            ArrayList<Integer> list = new ArrayList<>();
            int m = matrix.length;
            int n = matrix[0].length;
            // 上边界
            int top = 0;
            // 右边界
            int right = n - 1;
            // 下边界
            int down = m - 1;
            // 左边界
            int left = 0;
            while (list.size()<m*n) {
                // 顶部 从左往右
                if (top<=down) {
                    for (int i = left; i <= right; i++) {
                        list.add(matrix[top][i]);
                    }
                    // 上边界下移
                    top++;
                }
                // 右侧 从上往下遍历
                if (left<=right) {
                    for (int i = top; i <= down; i++) {
                        list.add(matrix[i][right]);
                    }
                    right--;
                }
                // 底部 从右往左遍历
                if (top<=down) {
                    for (int i = right; i >= left; i--) {
                        list.add(matrix[down][i]);
                    }
                    down--;
                }
                // 左侧  从小往上遍历
                if (left<=right) {
                    for (int i = down; i >= top; i--) {
                        list.add(matrix[i][left]);
                    }
                    left++;
                }
            }
            return list;
        }
    }
}
