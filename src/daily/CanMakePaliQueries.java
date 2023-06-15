package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 1177. 构建回文串检测[medium]
 * https://leetcode.cn/problems/can-make-palindrome-from-substring/
 */
public class CanMakePaliQueries {
    public static void main(String[] args) {
        String s = "hunu";
        int[][] queries = {{1, 1, 1}, {2, 3, 0}, {3, 3, 1}, {0, 3, 2}, {1, 3, 3}, {2, 3, 1}, {3, 3, 1}, {0, 3, 0}, {1, 1, 1}, {2, 3, 0}, {3, 3, 1}, {0, 3, 1}, {1, 1, 1}};
        Solution solution = new Solution();
        List<Boolean> list = solution.canMakePaliQueries(s, queries);
        for (Boolean aBoolean : list) {
            System.out.print(aBoolean + " ");
        }
    }

    static class Solution {
        public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
            int n = s.length();
            // 前缀和 sum[i+1][j]记录从字符串s[0,i]中字母表中第j个小写字母出现的次数
            int[][] sum = new int[n + 1][26];
            for (int i = 0; i < n; i++) {
                // 将s[0,i-1]之前统计的情况叠加到本次
                sum[i + 1] = sum[i].clone();
                sum[i + 1][s.charAt(i) - 'a']++;
                // 只记录奇偶性即可 奇数为1偶数为0
                sum[i + 1][s.charAt(i) - 'a'] %=2;
            }

            ArrayList<Boolean> ans = new ArrayList<>(queries.length);
            for (int[] query : queries) {
                int left = query[0];
                int right = query[1];
                int k = query[2];
                int m = 0;
                // 对于每个query,利用前缀和计算出每种字母出现次数，统计有多少种字母出现奇数次
                for (int j = 0; j < 26; j++) {
                    m += (sum[right + 1][j] != sum[left][j] ? 1 : 0);  // 奇数个数m+=1,偶数个m+=0
                }
                // 循环结束，共有m种字母出现奇数次
                // 只需要改m/2个字母就可以排列成回文
                ans.add(m / 2 <= k);
            }
            return ans;
        }
    }
}
