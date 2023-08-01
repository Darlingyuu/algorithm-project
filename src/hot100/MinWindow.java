package hot100;

import java.util.HashMap;

/**
 * 76. 最小覆盖子串[hard]
 * https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&envId=top-100-liked
 */
public class MinWindow {
    public static void main(String[] args) {
        System.out.println(minWindow("a", "aa"));
    }

    static public String minWindow(String s, String t) {
        // 存放需要的字符及个数
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        // 记录最小子串的起始下标
        int start=0;
        // 长度
        int len=Integer.MAX_VALUE;

        // 记录窗口内的字符及个数
        HashMap<Character, Integer> window = new HashMap<>();
        // 达到数量要求的字符个数
        int valid = 0;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                // 加入
                window.put(c, window.getOrDefault(c, 0) + 1);
                // c达到数量
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 收缩窗口
            while (valid == need.size()) {
                // 更新最小子串信息
                if (left>right-left){
                    start=left;
                    left=right-left;
                }
                char a = s.charAt(left);
                left++;
                if (need.containsKey(a)) {
                    if (window.get(a).equals(need.get(a))) {
                        valid--;
                    }
                    window.put(a, window.get(a) - 1);
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }
}
