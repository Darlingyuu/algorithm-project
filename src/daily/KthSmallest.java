package daily;

import java.util.Arrays;

/**
 * 1439. 有序矩阵中的第 k 个最小数组和[hard]
 * https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
 */
public class KthSmallest {
    public static void main(String[] args) {

    }


    class Solution {
        // 暴力解法
        public int kthSmallest(int[][] mat, int k) {
            int[] a = {0};
            for (int[] row : mat) {
                int[] b = new int[row.length * a.length];
                int i=0;
                for (int x : a) {
                    for (int y : row) {
                        b[i++]=x+y;
                    }
                }
                Arrays.sort(b);
                if (b.length>k){// 保留最小的k个
                    b=Arrays.copyOfRange(b,0,k);
                }
                a=b;
            }
            return a[k-1];
        }

    }
}
