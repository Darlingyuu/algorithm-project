package daily;

import java.util.Arrays;

/**
 * 2451. 差值数组不同的字符串[easy]
 * https://leetcode.cn/problems/odd-string-difference/
 */
public class OddString {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        String[] words={"adc","wzy","abc"};
        String[] words={"mll","edd","jii","tss","fee","dcc","nmm","abb","utt","zyy","xww","tss","wvv","xww","utt"};
        System.out.println(solution.oddString(words));
    }

    static class Solution {
        public String oddString(String[] words) {
            int length = words[0].length();
            int[] diff1=getDiff(words[0]);
            int[] diff2=getDiff(words[1]);
            // 判断diff1 diff2是否相等
            if (Arrays.equals(diff1,diff2)){
                // diff1 diff2相等，直接找与其不相等的即可
                for (int i = 2; i < words.length; i++) {
                    int[] diff = getDiff(words[i]);
                    if (!Arrays.equals(diff,diff1)){
                        return words[i];
                    }
                }
            }
            // diff1 diff2不相等，下一个与谁相等那另一个就是要找的
            int[] diff = getDiff(words[2]);
            return Arrays.equals(diff,diff1)? words[1]:words[0];


        }

        private int[] getDiff(String word){
            int[] diff = new int[word.length() - 1];
            for (int j = 0; j < word.length() - 1; j++) {
                diff[j]=word.charAt(j+1)-word.charAt(j);
            }
            return diff;
        }

    }

}
