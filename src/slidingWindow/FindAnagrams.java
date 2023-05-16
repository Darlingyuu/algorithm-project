package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词[medium]
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 */
public class FindAnagrams {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> list = solution.findAnagrams("cbaebabacd", "abc");
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
    }

    static class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            HashMap<Character, Integer> need = new HashMap<>();
            HashMap<Character, Integer> window = new HashMap<>();
            ArrayList<Integer> res = new ArrayList<>();

            for (char c : p.toCharArray()) {
                need.put(c,need.getOrDefault(c,0)+1);
            }

            int left=0,right=0;
            int valid=0;
            while (right<s.length()){
                //将要移入窗口的字符
                char a = s.charAt(right);
                right++;
                // 判断是否是需要的字符
                if (need.containsKey(a)){
                    // 是需要的字符，放入窗口内并记录次数
                    window.put(a,window.getOrDefault(a,0)+1);
                    // 判断是否达到了需要a的个数，达到valid+1
                    if (need.get(a).equals(window.get(a))) valid++;
                }

                // 收缩窗口
                // 长度达到P的长度
                while (right-left>=p.length()){
                    //满足条件记录起始索引
                    if (valid==need.size()) {
                        res.add(left);;
                    }
                    //将要移出窗口的字符
                    char c = s.charAt(left);
                    left++;
                    // 判断是否是需要的字符
                    if (need.containsKey(c)){
                        // 判断是否达到了需要a的个数，达到valid-1(移出之后数量不再满足)
                        if (need.get(c).equals(window.get(c))) valid--;
                        // 是需要的字符，移出窗口内并记录次数
                        window.put(c,window.get(c)-1);
                    }
                }
            }
            return res;
        }
    }
}
