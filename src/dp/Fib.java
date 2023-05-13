package dp;

/**
 * 509. 斐波那契数[easy]
 * https://leetcode.cn/problems/fibonacci-number/
 */
public class Fib {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fib(4));
    }

    static class Solution {
        public int fib(int n) {
            return dp(n);
        }

        int dp_i_2=0,dp_i_1=1;
        // n的斐波那契数是dp(n)
        private int dp(int n) {
            // base case
            if (n==0 || n==1) return n;

            for (int i = 2; i <= n; i++) {
                int res=dp_i_2+dp_i_1;
                dp_i_2=dp_i_1;
                dp_i_1=res;
            }
            return dp_i_1;
        }


        public int fibII(int n) {
            //边界条件
            if (n==0){
                return 0;
            }
            if (n==1){
                return 1;
            }

            return fibII(n-1)+fibII(n-2);
        }
    }
}
