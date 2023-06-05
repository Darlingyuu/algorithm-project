package dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 140. 单词拆分 II[hard]
 * https://leetcode.cn/problems/word-break-ii/
 */
public class WordBreakII {
    public static void main(String[] args) {
        String s="catsanddog";
        List<String> wordDict= Arrays.asList("cat","cats","and","sand","dog");
        Solution solution = new Solution();
        List<String> list = solution.wordBreak(s, wordDict);
        for (String string : list) {
            System.out.println(string);
        }


    }

    static class Solution {
        // 记录结果
        List<String> res = new LinkedList<>();
        // 记录回溯算法的路径
        LinkedList<String> track = new LinkedList<>();
        List<String> wordDict;
        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            // 执行回溯算法穷举所有可能的组合
            backtrack(s, 0);
            return res;
        }

        private void backtrack(String s, int i) {
            // base case
            if (i==s.length()){
                // 找到一个合法组合
                res.add(String.join(" ",track));
                return;
            }

            for (String word : wordDict) {
                // 看看哪个单词能匹配s[i..]的前缀
                int len = word.length();
                if (i+len<=s.length() && s.substring(i,i+len).equals(word)){
                    // 找到一个单词匹配s[i,i+len）
                    // 做选择
                    track.add(word);
                    // 进入下一层，继续匹配s[i+len...]
                    backtrack(s,i+len);
                    // 撤销选择
                    track.removeLast();
                }
            }
        }


    }
}
