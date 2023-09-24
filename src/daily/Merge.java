package daily;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组[easy]
 * https://leetcode.cn/problems/merge-sorted-array/
 */
public class Merge {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        solution.merge(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));
    }

    static class Solution {
        // 常数空间 从尾部向前遍历排序
        public void mergeII(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1, p2 = n - 1;
            int tail = m + n - 1;
            int cur;
            while (p1 >= 0 || p2 >= 0) {
                if (p1 == -1) {
                    cur = nums2[p2--];
                } else if (p2 == -1) {
                    cur = nums1[p1--];
                } else if (nums1[p1] > nums2[p2]) {
                    cur = nums1[p1--];
                } else {
                    cur = nums2[p2--];
                }
                nums1[tail--] = cur;
            }
        }

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] tmp = Arrays.copyOfRange(nums1, 0, m);
            int p = 0;
            int q = 0;
            int i = 0;
            while (p < m && q < n) {
                if (tmp[p] <= nums2[q]) {
                    nums1[i] = tmp[p++];
                } else {
                    nums1[i] = nums2[q++];
                }
                i++;
            }
            while (p < m) {
                nums1[i] = tmp[p++];
                i++;
            }
            while (q < n) {
                nums1[i] = nums2[q++];
                i++;
            }
        }
    }
}
