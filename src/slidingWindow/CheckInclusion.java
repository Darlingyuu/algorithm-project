package slidingWindow;

import java.util.HashMap;

/**
 * 567. 字符串的排列[medium]
 * https://leetcode.cn/problems/permutation-in-string/
 */
public class CheckInclusion {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
        System.out.println(solution.checkInclusion("ab", "eidboaoo"));

    }

    static class Solution {
        public boolean checkInclusion(String s1, String s2) {
            HashMap<Character, Integer> need = new HashMap<>();
            HashMap<Character, Integer> window = new HashMap<>();

            for (char c : s1.toCharArray()) {
                need.put(c,need.getOrDefault(c,0)+1);
            }

            int left=0,right=0;
            int valid=0;
            while (right<s2.length()){
                //将要移入窗口的字符
                char a = s2.charAt(right);
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
                while (right-left>=s1.length()){
                    //满足条件直接返回true
                    if (valid==need.size()) {
                        return true;
                    }

                    //将要移出窗口的字符
                    char c = s2.charAt(left);
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
            return false;
        }
    }
}
