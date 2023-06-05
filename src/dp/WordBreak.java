package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 139. 单词拆分[medium]
 * https://leetcode.cn/problems/word-break/
 */
public class WordBreak {
    public static void main(String[] args) {
        String s="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict= Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        Solution solution = new Solution();
        System.out.println(solution.wordBreak(s, wordDict));


    }

    static class Solution {
        private HashSet<String> set;
        // 备忘录，-1 代表未计算，0 代表无法凑出，1 代表可以凑出
        int[] memo;
        public boolean wordBreak(String s, List<String> wordDict) {
            if (s.equals("")) return true;
            // 方便判断字符串是否存在在字典中
            set = new HashSet<String>(wordDict);
            // 备忘录初始化为 -1
            memo=new int[s.length()];
            Arrays.fill(memo,-1);

            return wordBreak(s,0);
        }

        // 判断s[index...]字符串是否可以由字典拼接而成
        private boolean wordBreak(String s, int index){
            // base case
            if (index==s.length()){
                // 从子问题进入，s比较完了
                return true;
            }
            // 防止冗余计算
            if (memo[index]!=-1) {
                return memo[index]==0?false:true;
            }

            // 遍历 s[index..] 的所有前缀  [index,i)的子串
            for (int i = index+1; i <= s.length(); i++) {
                // s[index, i)
                String prefix = s.substring(index, i);
                if (set.contains(prefix)){
                    // 继续比较子问题 s[index...]
                    boolean subProblem = wordBreak(s, i);
                    // s[i...] 可以拼出，且s[index, i)的子串也在字典内 则s[index]也可以拼出
                    if (subProblem){
                        memo[index]=1;
                        return true;
                    }
                }
            }
            // s比较完没有进入子问题，s[index...]无法拼出
            memo[index]=0;
            return false;
        }
    }
}
