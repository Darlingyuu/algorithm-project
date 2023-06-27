package dp;

import java.util.Arrays;

/**
 * 1312. 让字符串成为回文串的最少插入次数[hard]
 * https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 */
public class MinInsertions {
    class Solution {
        int[][] memo;
        public int minInsertions(String s) {
            int length = s.length();
            memo = new int[length][length];
            for (int i = 0; i < length; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(s,0,length-1);
        }

        // dp(s,i,j)返回s[i,j]成为回文串的最少操作次数
        private int dp(String s, int i, int j) {
            // 遍历结束
            if (i>=j) return 0;

            if (memo[i][j]!=-1) return memo[i][j];

            int res=0;
            if (s.charAt(i)==s.charAt(j)){
                // 无需操作
                res=dp(s,i+1,j-1);
            }else {
                res=Math.min(dp(s, i, j-1)+1, // 在s[i]处插入s[j]相同的字符，操作数+1
                        dp(s, i+1, j)+1); // 在s[j]处插入s[i]相同的字符，操作数+1
            }
            return memo[i][j]=res;
        }
    }
}
