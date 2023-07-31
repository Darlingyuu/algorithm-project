package hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词[medium]
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked
 */
public class FindAnagrams {
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res=new ArrayList<>();
            // 记录p中的字符和字符个数
            HashMap<Character, Integer> pMap = new HashMap<>();
            for (char c : p.toCharArray()) {
                pMap.put(c,pMap.getOrDefault(c,0)+1);
            }

            // 滑动窗口
            HashMap<Character, Integer> window = new HashMap<>();
            int left=0,right=0;
            // 记录达到需要字符及数量的个数
            int valid=0;
            while (right<s.length()){
                // 要加入的字符
                char c = s.charAt(right++);
                // 判断是否是需要的字符
                if (pMap.containsKey(c)){
                    // 是需要的字符，放入窗口内并记录次数
                    window.put(c,window.getOrDefault(c,0)+1);
                    // 判断是否达到需要c的个数
                    if (pMap.get(c).equals(window.get(c))) valid++;
                }


                // 收缩窗口  长度达到p
                while (right-left>=p.length()){
                    // 瞒住条件记录起始索引
                    if (pMap.size()==valid){
                        res.add(left);
                    }
                    //将要移出窗口的字符
                    char a = s.charAt(left++);
                    // 判断是否是需要的字符
                    if (pMap.containsKey(c)){
                        // 判断是否达到了需要a的个数，达到valid-1(移出之后数量不再满足)
                        if (pMap.get(c).equals(window.get(c))) valid--;
                        // 是需要的字符，移出窗口内并记录次数
                        window.put(c,window.get(c)-1);
                    }
                }
            }
            return res;
        }
    }
}
