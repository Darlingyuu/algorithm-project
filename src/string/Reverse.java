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

        public int reverseII(int x) {
            long res = 0;
            while (x != 0) {
                res=res*10 + x%10;
                x=x/10;
            }
            return (int)res==res?(int)res:0;
        }
    }
}
