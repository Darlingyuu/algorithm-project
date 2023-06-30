package daily;

/**
 * 2490. 回环句[easy]
 * https://leetcode.cn/problems/circular-sentence/
 */
public class IsCircularSentence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isCircularSentence("leetcode exercises sound delightful"));
    }

    static class Solution {
        public boolean isCircularSentence(String sentence) {
            String[] strings = sentence.split(" ");
            int n = strings.length;
            for (int i = 0; i < n; i++) {
                if (i==n-1){
                    if ( !check(strings,i,0)) return false;
                }else {
                    if ( !check(strings,i,i+1)) return false;
                }
            }
            return true;
        }

        // 第i个单词的最后一个字符和第j个单词的第一个字符是否相等
        private boolean check(String[] strings, int i, int j) {
            return strings[i].charAt(strings[i].length()-1)==strings[j].charAt(0);
        }
    }
}
