package dp;

import java.util.Arrays;

/**
 * 583. 两个字符串的删除操作[medium]
 * https://leetcode.cn/problems/delete-operation-for-two-strings/
 */
public class MinDistanceByDel {

    class Solution {
        int m;
        int n;
        int[][] memo;
        public int minDistance(String word1, String word2) {
            m = word1.length();
            n = word2.length();
            // 得到公共子序列的长度
            int len=longestCommonSubsequence(word1,word2);
            // 删除操作数
            return m-len+n-len;
        }


        // 得到公共子序列的长度
        public int longestCommonSubsequence(String text1, String text2) {
            memo = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(text1,0,text2,0);
        }

        // dp(text1,p,text2,q) 返回text1[p,..] 和text2[q,..]的最长公共子序列的长度
        private int dp(String text1, int p, String text2, int q) {
            if (p==text1.length() || q==text2.length()){
                // 匹配完了
                return 0;
            }
            if (memo[p][q]!=-1) return memo[p][q];

            int res=0;
            // p q指向放入字符相同,说明该字符是公共子序列的一部分
            if (text1.charAt(p)==text2.charAt(q)){
                // 无需操作，继续匹配后面的
                res=dp(text1, p+1, text2, q+1)+1;
            }else {
                res=Math.max(dp(text1, p+1, text2, q), // text1[p]不是公共子序列的一部分,删除
                        dp(text1, p, text2, q+1)); // text2[q]不是公共子序列的一部分，删除
            }
            return memo[p][q]=res;
        }
    }
}
