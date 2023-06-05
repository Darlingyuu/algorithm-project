package dp;

import java.util.Arrays;

/**
 * 115. 不同的子序列[hard]
 * https://leetcode.cn/problems/distinct-subsequences/
 */
public class NumDistinct {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDistinct("babgbag", "bag"));
    }
    static class Solution {
        int[][] dp;
        public int numDistinct(String s, String t) {
            // dp[i][j]记录s[i..]的子序列中t[j..]出现的次数
            dp = new int[s.length()][t.length()];
            // 初始化
            for (int i = 0; i < s.length(); i++) {
                Arrays.fill(dp[i],-1);
            }
            return numDistinct(s,0,t,0);
        }

        // 统计s[i..]的子序列中t[j..]出现的次数
        private int numDistinct(String s, int i, String t, int j) {
            // base case
            // 1. t检测完了
            if (j==t.length()){
                return 1;
            }
            // 2. s剩余长度不足
            if (s.length()-i<t.length()-j){
                return 0;
            }

            // 利用dp记录，避免重复计算
            if (dp[i][j]!=-1) return dp[i][j];

            int res=0;
            // 比较s[i] t[j]
            if (s.charAt(i)==t.charAt(j)){
                // 匹配
                res+=numDistinct(s,i+1,t,j+1)+numDistinct(s,i+1,t,j);
            }else {
                // 不匹配
                res+=numDistinct(s,i+1,t,j);
            }
            dp[i][j]=res;
            return res;
        }
    }
}
