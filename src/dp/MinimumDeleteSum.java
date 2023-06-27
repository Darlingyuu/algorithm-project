package dp;

import java.util.Arrays;

/**
 * 712. 两个字符串的最小ASCII删除和[medium]
 * https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings/
 */
public class MinimumDeleteSum {
    class Solution {
        int[][] memo;
        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length();
            int n = s2.length();
            memo = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(s1,0,s2,0);
        }

        // dp(s1,p,s2,q)返回s1[p,..]和s2[q,..]相等所需删除字符的 ASCII 值的最小和
        private int dp(String s1, int p, String s2, int q) {
            int res=0;

            if (p==s1.length()){
                // s1匹配完成
                // s2中未匹配完的应都删除
                for (int i = q; i < s2.length(); i++) {
                    res+=s2.charAt(i);
                }
                return res;
            }

            if (q==s2.length()){
                // s2匹配完成
                // s1中未匹配完的应都删除
                for (int i = p; i < s1.length(); i++) {
                    res+=s1.charAt(i);
                }
                return res;
            }

            if (memo[p][q]!=-1) return memo[p][q];

            // p q指向的字符相同
            if (s1.charAt(p)==s2.charAt(q)){
                // 无需操作，继续匹配下一个
                res=dp(s1, p+1, s2, q+1);
            }else {
                res=Math.min(dp(s1, p+1, s2, q)+s1.charAt(p), // s1[p]要删除
                        dp(s1, p, s2, q+1)+s2.charAt(q)); // s2[q]要删除
            }
            return memo[p][q]=res;
        }
    }
}
