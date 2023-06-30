package dp;

import java.util.Arrays;

/**
 * 10. 正则表达式匹配[hard]
 * https://leetcode.cn/problems/regular-expression-matching/
 */
public class IsMatch {

    class Solution {
        int m;
        int n;
        int[][] memo;
        public boolean isMatch(String s, String p) {
            /**
             * 两个指针 i, j 分别在 s 和 p 上移动
             * 如果最后两个指针都能移动到字符串的末尾，那么久匹配成功，反之则匹配失败。
             * 「状态」无非就是i和j两个指针的位置，「选择」就是p[j]选择匹配几个字符。
             */
            m = s.length();
            n = p.length();
            // memo[i][j] 记录s[i..] p[j..]匹配情况  0失败1成功
            memo = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(s,0,p,0);
        }

        // dp(s,i,p,j) 返回s[i..] p[j..]匹配情况
        private boolean dp(String s, int i, String p, int j) {
            // base case
            // 模式串p已经被匹配完了，那么应该看看文本串s匹配到哪里了，如果s也恰好被匹配完，则说明匹配成功
            if (j == n) {
                return i == m;
            }

            // 比如s = "a", p = "ab*c*"
            if (i == m) {
                // 如果能匹配空串，一定是字符和 * 成对儿出现
                if ((n - j) % 2 == 1) {
                    return false;
                }
                // 检查是否为 x*y*z* 这种形式
                for (; j + 1 < n; j += 2) {
                    if (p.charAt(j + 1) != '*') {
                        return false;
                    }
                }
                return true;
            }

            if (memo[i][j]!=-1) return memo[i][j]==1;


            boolean res=false;
            if (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'){ // 匹配
                if (j<n-1 && p.charAt(j+1)=='*'){ // p[j+1]位置是*
                    // 可以用p[j]去匹配s 0次或者多次
                    // 0次，即不用s[i]与p[j]匹配，s[i]要继续与p[j+2]继续匹配
                    // 多次，即s[i]与p[j]匹配，并且s[i+1]继续和p[j]匹配
                    res= dp(s,i,p,j+2) || dp(s,i+1,p,j);
                }else { //  p[j+1]位置不是*，常规匹配一次
                    res= dp(s,i+1,p,j+1);
                }
            }else { // 不匹配
                if (j<n-1 && p.charAt(j+1)=='*'){ // p[j+1]位置是*
                    // 匹配0次,即不用s[i]与p[j]匹配，s[i]要继续与p[j+2]继续匹配
                    res= dp(s,i,p,j+2);
                }else {
                    // 无法继续匹配
                    res= false;
                }
            }
            memo[i][j]=res?1:0;
            return res;
        }
    }
}
