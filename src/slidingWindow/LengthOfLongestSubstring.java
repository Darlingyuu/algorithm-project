package slidingWindow;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串[medium]
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
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
