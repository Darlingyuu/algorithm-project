package daily;

/**
 * 1281. 整数的各位积和之差[easy]
 * https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 */
public class SubtractProductAndSum {
    class Solution {
        public int subtractProductAndSum(int n) {
            String num = String.valueOf(n);
            int multi = 1;
            int sum = 0;
            for (int i = 0; i < num.length(); i++) {
                multi *= num.charAt(i) - '0';
                sum += num.charAt(i) - '0';
            }
            return multi - sum;
        }
    }
}
