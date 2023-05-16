package slidingWindow;

import java.util.HashMap;

/**
 * 76. 最小覆盖子串[hard]
 * https://leetcode.cn/problems/minimum-window-substring/
 */
public class MinWindow {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(solution.minWindow("cabwefgewcwaefgcf", "cae"));
//        System.out.println(solution.minWindow("a", "aa"));
    }

    static class Solution {
        public String minWindow(String s, String t) {
            HashMap<Character, Integer> need = new HashMap<>();
            HashMap<Character, Integer> window = new HashMap<>();

            for (char c : t.toCharArray()) {
                need.put(c,need.getOrDefault(c,0)+1);
            }

            int left=0,right=0;
            int valid=0;
            int len=Integer.MAX_VALUE;
            int start=0;
            while (right<s.length()){
                //将要移入窗口的字符
                char a = s.charAt(right);
                right++;
                if (need.containsKey(a)){
                    window.put(a,window.getOrDefault(a,0)+1);
                    if (need.get(a).equals(window.get(a))) valid++;
                }

                //收缩窗口
                while (valid==need.size()){
                    //更新最后的截取起始索引以及长度
                    if (right - left < len) {
                        start = left;
                        len = right - left;
                    }
                    len=Math.min(len,right-left);
                    //将移出的字符
                    char c = s.charAt(left);
                    left++;
                    if (need.containsKey(c)){
                        if (need.get(c).equals(window.get(c))) valid--;
                        window.put(c,window.get(c)-1);

                    }
                }
            }
            return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
        }
    }
}
