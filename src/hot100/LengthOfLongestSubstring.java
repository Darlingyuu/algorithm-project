package hot100;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 3. 无重复字符的最长子串[medium]
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-100-liked
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring(" "));
    }

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length()==0) return 0;

            int longLen=0;

            int left=0,right=1;
            // 维护s[left,right) 不含重复字符
            HashSet<Character> set = new HashSet<>();
            set.add(s.charAt(left));
            while (right<s.length()){
                // 扩大右边界
                while (right<s.length()&&!set.contains(s.charAt(right))){
                    set.add(s.charAt(right++));
                }

                // 遇到重复字符，跳出上述循环
                // 更新longLen
                longLen=Math.max(longLen,set.size());

                // 收缩左边界
                while (right<s.length()&&set.contains(s.charAt(right))){
                    set.remove(s.charAt(left++));
                }
            }
            // 更新longLen
            longLen=Math.max(longLen,set.size());

            return longLen;
        }

        public int lengthOfLongestSubstringII(String s) {
            //记录窗口
            HashMap<Character, Integer> window = new HashMap<>();

            int left=0,right=0;
            int len=0;
            while (right<s.length()){
                //将要放入窗口的字符
                char a = s.charAt(right);
                right++;
                // 加入窗口并记录次数
                window.put(a,window.getOrDefault(a,0)+1);

                //收缩窗口
                //加入当前字符后，窗口中出现重复字符
                while (window.get(a)>1){
                    //将要移出窗口的字符
                    char c = s.charAt(left);
                    left++;
                    //移出窗口并记录次数
                    window.put(c,window.get(c)-1);

                }
                //更新长度
                len=Math.max(len,right-left);
            }
            return len;
        }
    }
}
