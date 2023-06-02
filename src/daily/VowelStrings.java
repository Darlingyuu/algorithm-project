package daily;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2559. 统计范围内的元音字符串数[medium]
 * https://leetcode.cn/problems/count-vowel-strings-in-ranges/
 */
public class VowelStrings {
    public static void main(String[] args) {
        String[] words = {"aba","bcb","ece","aa","e"};
        int[][] queries = {{0,2},{1,4},{1,1}};
        Solution solution = new Solution();
        int[] ints = solution.vowelStrings(words, queries);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] vowelStrings(String[] words, int[][] queries) {
            ArrayList<String> vowelList = new ArrayList<>();
            String[] vol={"a","e","i","o","u"};
            for (String s : vol){
                vowelList.add(s);
            }

            int n = words.length;
            // preSum[i] 统计的是[1,i]以元音开头和结尾的字符串的数目。
            int[] preSum = new int[n+1];
            for (int i = 0; i < n; i++) {
                if (vowelList.contains(words[i].substring(0,1)) &&
                        vowelList.contains(words[i].substring(words[i].length()-1))){
                    preSum[i+1]=preSum[i]+1;
                }else {
                    preSum[i+1]=preSum[i];
                }
            }

            int[] res = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                res[i]=preSum[queries[i][1]+1]-preSum[queries[i][0]];
            }
            return res;
        }
    }
}
