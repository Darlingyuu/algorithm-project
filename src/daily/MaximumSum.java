package daily;

import java.util.Arrays;

/**
 * 1186. 删除一次得到子数组最大和[medium]
 * https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/
 */
public class MaximumSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr={-1,-1,-1,-1};
        System.out.println(solution.maximumSum(arr));
    }
    static class Solution {
        public int maximumSum(int[] arr) {
            int n = arr.length;
            if (n == 1) {
                return arr[0];
            }
            // dp[i+1][0]表示以arr[i]结尾的子数组，且未删除元素的元素和
            // dp[i+1][1]表示以arr[i]结尾的子数组，且删除一个元素的元素和
            int[][] dp = new int[n+1][2];
            // 子数组不能为空
            Arrays.fill(dp[0],Integer.MIN_VALUE/2);  // 除 2 防止负数相加溢出


            int ans=Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                // 以arr[i-1]结尾，不删除元素情况下,那么以arr[i-2]结尾时也没有删除元素
                dp[i][0]=Math.max(dp[i-1][0],0)+arr[i-1];
                // 以arr[i-1]结尾，删除一个元素情况下
                // 1. 以arr[i-2]结尾时删除过一个元素
                // 2. 以arr[i-2]结尾时乜有删除元素，此次删除arr[i-1]
                dp[i][1]=Math.max(dp[i-1][1]+arr[i-1],dp[i-1][0]);
                ans=Math.max(ans,Math.max(dp[i][0],dp[i][1]));
            }
            return ans;
        }

        // 空间优化
        public int maximumSumII(int[] arr) {
            int ans = Integer.MIN_VALUE / 2, f0 = ans, f1 = ans;
            for (int x : arr) {
                f1 = Math.max(f1 + x, f0);
                f0 = Math.max(f0, 0) + x;
                ans = Math.max(ans, Math.max(f0, f1));
            }
            return ans;
        }



    }
}
