package string;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/10/9 16:48
 */
public class Reverse {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseII(123));
    }

    static class Solution {
        public int reverse(int x) {
            int res = 0;
            while (x != 0) {
                int t = x % 10;
                int newRes = res * 10 + t;
                //如果数字溢出，直接返回0
                if ((newRes - t) / 10 != res)
                    return 0;
                res = newRes;
                x = x / 10;
            }
            return res;
        }

        /**
         * 经过 res = res * 10 + x % 10 这一步后：
         * 1.如果res超出最大值，则它会由正数变成一个负数
         * 2.如果res超出最小值，则它会由负数变成一个正数
         * 总之这两种情况中res的正负号肯定与x是相反的
         * 所以，如果result超出范围，result%10与x%10肯定是一个为正数，一个为负数，不可能相等，反之如果result没有超出范围，result%10与x%10肯定相等
         * @param x
         * @return
         */
        public int reverseII(int x) {
            int res = 0;
            while (x != 0) {
                res = res * 10 + x % 10;
                if (res % 10 != x % 10) {
                    return 0;
                }
                x = x / 10;
            }
            return res;
        }
    }
}
