package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 187. 重复的DNA序列[medium]
 * https://leetcode.cn/problems/repeated-dna-sequences/description/
 */
public class FindRepeatedDnaSequences {
    public static void main(String[] args) {
        String s = "AAAAAAAAAAAAA";
        Solution solution = new Solution();
        List<String> list = solution.findRepeatedDnaSequences(s);
        for (String string : list) {
            System.out.println(string);
        }
    }

    static class Solution {
        public List<String> findRepeatedDnaSequences(String s) {
            HashSet<String> res = new HashSet<>();
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i+10 <= s.length(); i++) {
                String substring = s.substring(i, i + 10);
                if (!set.contains(substring)){
                    set.add(substring);
                }else{
                    res.add(substring);
                }
            }
            return new ArrayList<>(res);
        }
    }
}
