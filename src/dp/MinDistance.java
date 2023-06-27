package dp;

import java.util.Arrays;

/**
 * 72. 编辑距离[medium]
 * https://leetcode.cn/problems/edit-distance/
 */
public class MinDistance {
    class Solution {
        int[][] memo;
        public int minDistance(String word1, String word2) {
            memo = new int[word1.length()][word2.length()];
            for (int i = 0; i < word1.length(); i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(word1,word1.length()-1,word2,word2.length()-1);
        }

        // dp[word1,p,word2,q]返回word[0,p]转换成word2[0,1]的最小操作数
        private int dp(String word1, int p, String word2, int q) {
            // base case
            if (p==-1){
                // word1匹配完，将word2未匹配完的部分都删除，其操作数为q+1;
                return q+1;
            }
            if (q==-1){
                return p+1;
            }

            if (memo[p][q]!=-1) return memo[p][q];

            int res=0;
            // p q指向的字符相同，不用操作
            if (word1.charAt(p)==word2.charAt(q)){
                // 继续向前匹配
                res= dp(word1,p-1,word2,q-1);
            }else { // p q指向的字符不同
                // 取插入、删除、替换最小操作数
                res= min(dp(word1, p, word2, q-1)+1, // word1[p]位置插入字符
                        dp(word1, p-1, word2, q)+1, // word1[p]位置删除字符
                        dp(word1, p-1, word2, q-1)+1);  // word1[p]位置替换字符
            }
            return memo[p][q]=res;
        }

        private int min(int a,int b,int c){
            return Math.min(a,Math.min(b,c));
        }
    }
}
