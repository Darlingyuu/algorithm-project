package daily;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2679. 矩阵中的和[medium]
 * https://leetcode.cn/problems/sum-in-a-matrix/
 */
public class MatrixSum {
    public static void main(String[] args) {
        int[][] nums={{7,2,1},{6,4,2},{6,5,3},{3,2,1}};
        Solution solution = new Solution();
        System.out.println(solution.matrixSum(nums));
    }

    static class Solution {
        public int matrixSum(int[][] nums) {
            int m = nums.length;
            int n = nums[0].length;
            for (int i = 0; i < m; i++) {
                Arrays.sort(nums[i]);
            }

            int res = 0;
            for (int i = n - 1; i >= 0; i--) {
                PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
                for (int j = 0; j < m; j++) {
                    queue.add(nums[j][i]);
                }
                res+=queue.poll();
            }
            return res;
        }
    }
}
