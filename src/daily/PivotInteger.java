package daily;

/**
 * 2485. 找出中枢整数[easy]
 * https://leetcode.cn/problems/find-the-pivot-integer/
 */
public class PivotInteger {

    class Solution {
        public int pivotInteger(int n) {
            // 等差
            int sum=(1+n)*n/2;

            int s=0;
            for (int i = 1; i <= n; i++) {
                s+=i;
                if (s==sum) return i;
                sum-=i;
            }
            return -1;
        }
    }
}
