package string;

/**
 * 151. 反转字符串中的单词[medium]
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 */
public class ReverseWords {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("a good   example"));
    }

    static class Solution {
        public String reverseWords(String s) {
            String[] strings = s.trim().split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = strings.length-1; i >= 0; i--) {
                if (!strings[i].equals("")){
                    builder.append(strings[i]).append(" ");
                }
            }
            String string = builder.toString();
            return string.substring(0,string.length()-1);
        }
    }
}
