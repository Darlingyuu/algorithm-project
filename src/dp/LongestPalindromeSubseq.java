package dp;

import java.util.Arrays;

/**
 * 516. 最长回文子序列[medium]
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromeSubseq {

    class Solution {
        int[][] memo;
        public int longestPalindromeSubseq(String s) {
            int length = s.length();
            memo = new int[length][length];
            for (int i = 0; i < length; i++) {
                Arrays.fill(memo[i],-1);

            }
            return dp(s,0,length-1);
        }

        // dp(s,i,j)返回s[i,j]最长回文子序列
        private int dp(String s, int i,int j) {
            if (i==j) return 1;
            if (i+1==j && s.charAt(i)==s.charAt(j)) return 2;
            if (i>j) return 0;

            if (memo[i][j]!=-1) return memo[i][j];

            int len=0;
            // i j指向的字符相同
            if (s.charAt(i)==s.charAt(j)){
                len=dp(s,i+1,j-1)+2;
            }else {
                len=Math.max(dp(s,i+1,j),dp(s,i,j-1));
            }
            return memo[i][j]=len;
        }
    }
}
