package daily;

/**
 * 2481. 分割圆的最少切割次数[easy]
 * https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle/
 */
public class NumberOfCuts {
    class Solution {
        public int numberOfCuts(int n) {
            if (n==1) return 0;

            if (n%2==0){
                return n/2;
            }else {
                return n;
            }
        }
    }
}
