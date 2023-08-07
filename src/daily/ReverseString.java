package daily;

/**
 * 344. 反转字符串[easy]
 * https://leetcode.cn/problems/reverse-string/
 */
public class ReverseString {
    class Solution {
        public void reverseString(char[] s) {
            int l = 0;
            int r = s.length - 1;
            while (l < r) {
                char temp = s[l];
                s[l] = s[r];
                s[r] = temp;
                l++;
                r--;
            }
        }
    }
}
