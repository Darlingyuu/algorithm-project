package daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 1156. 单字符重复子串的最大长度[medium]
 * https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/
 */
public class MaxRepOpt1 {
    public static void main(String[] args) {
        String text = "ababa";
        Solution solution = new Solution();
        System.out.println(solution.maxRepOpt1(text));
    }

    static class Solution {

        public int maxRepOpt1(String text) {
            // 记录每个字符及出现的次数
            Map<Character, Integer> count = new HashMap<Character, Integer>();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                count.put(c, count.getOrDefault(c, 0) + 1);
            }

            // 滑动窗口
            int res = 0;
            for (int i = 0; i < text.length(); ) {
                // 以i的字符为标准

                // step1: 找出当前连续的一段 [i, j)
                int j = i;
                while (j < text.length() && text.charAt(j) == text.charAt(i)) {
                    j++;
                }
                // 这段连续长度
                int curCnt = j - i;

                // step2: 如果这一段长度小于该字符出现的总数，并且前面或后面有空位，则使用 curCnt + 1 更新答案
                if (curCnt < count.getOrDefault(text.charAt(i), 0) &&
                        (j < text.length() || i > 0)) {
                    res = Math.max(res, curCnt + 1);
                }

                // step3: 找到这一段后面与之相隔一个不同字符的另一段 [j + 1, k)，如果不存在则 k = j + 1
                int k = j + 1;
                while (k < text.length() && text.charAt(k) == text.charAt(i)) {
                    k++;
                }

                res = Math.max(res, Math.min(k - i, count.getOrDefault(text.charAt(i), 0)));

                // 下一个对象直接从下一个不同于i处的字符开始
                i = j;
            }
            return res;

        }
    }

}
