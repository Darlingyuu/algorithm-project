package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵[medium]
 * https://leetcode.cn/problems/spiral-matrix/?envType=study-plan-v2&envId=top-100-liked
 */
public class SpiralOrder {
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            List<Integer> list = new ArrayList<>();
            // 上边界
            int top=0;
            // 下边界
            int down=m-1;
            // 左边界
            int left = 0;
            // 右边界
            int right=n-1;
            while (list.size()<m*n){
                // 上边界，从左往右，到达右边界
                if (top<=down){
                    for (int i = left; i <=right ; i++) {
                        list.add(matrix[top][i]);
                    }
                }
                // 上边界收缩下移
                top++;

                // 右边界，从上往下，到达下边界
                if (left<=right){
                    for (int i = top; i <=down ; i++) {
                        list.add(matrix[i][right]);
                    }
                }
                // 右边界收缩左移
                right--;


                // 下边界，从右往左，到达左边界
                if (top<=down){
                    for (int i = right; i >=left ; i--) {
                        list.add(matrix[down][i]);
                    }
                }
                // 下边界收缩上移
                down--;


                // 左边界，从下往上，到达上边界
                if (left<=right){
                    for (int i = down; i >=top ; i--) {
                        list.add(matrix[i][left]);
                    }
                }
                // 左边界收缩右移
                left++;
            }
            // 退出循环
            return list;
        }
    }
}