package string;

/**
 * 5. 最长回文子串 [medium]
 * https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class LongestPalindrome {
    public static void main(String[] args) {
//        String s ="babad";
        String s ="cbbd";
        Solution solution = new Solution();
        String palindrome = solution.longestPalindrome(s);
        System.out.println(palindrome);
    }

    static class Solution {
        public String longestPalindrome(String s) {
            String res="";
            //遍历s，根据不同位置作为中点来得到最长的回文串
            for (int i = 0; i < s.length(); i++) {
                // 长度奇数
                String s1 = getLongestPalindromeByMid(s, i, i);
                //长度偶数
                String s2 = getLongestPalindromeByMid(s, i, i+1);
                res=res.length()>s1.length()?res:s1;
                res=res.length()>s2.length()?res:s2;
            }
            return res;
        }

        // left=right  说明回文串长度为奇数，中点为一个，否则为两个
        private String getLongestPalindromeByMid(String s,int left,int right){
            while (left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
                left--;
                right++;
            }
            return s.substring(left+1,right);
        }
    }
}
