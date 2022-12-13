package string;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/12/13 21:41
 *
 *
 * 验证回文串:
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/xne8id/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class IsPalindrome {
    public static void main(String[] args) {
//        String s = "A man, a plan, a canal: Panama";
//        String s = "race a car";
        String s = "0P";
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(s));
    }

    static class Solution {
        public boolean isPalindrome(String s) {
            char[] array = s.toCharArray();
            StringBuilder str=new StringBuilder();
            for (char c : array) {
                if ((c >= 'a' && c<='z') || (c>='A' && c<='Z') || (c>=48 && c<=57)){
                    str.append(c);
                }
            }
            String res = str.toString();
            res=res.toLowerCase();
            for (int i = 0; i < res.length()/2; i++) {
                if (res.charAt(i)!=res.charAt(res.length()-1-i)){
                    return false;
                }
            }
            return true;
        }
    }
}
