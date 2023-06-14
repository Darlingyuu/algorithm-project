package daily;

/**
 * 1375. 二进制字符串前缀一致的次数[medium]
 * https://leetcode.cn/problems/number-of-times-binary-string-is-prefix-aligned/
 */
public class NumTimesAllBlue {
    public static void main(String[] args) {

    }

    class Solution {
        /**
         * 由于题目保证flips是范围 [1,n]中所有整数构成的一个排列，所以前 i个数互不相同。
         * 如果前 i个数的最大值等于 i，则说明找到了[1,i]内的所有整数。
         * （如果有一个数没找到，那这个数必然大于 i，与最大值等于 i 矛盾。）
         */
        public int numTimesAllBlue(int[] flips) {
            int n = flips.length;
            int ans=0;
            // 记录[0,i]的最大值
            int mx=0;
            for (int i = 0; i < n; i++) {
                mx=Math.max(mx,flips[i]);
                if (mx==i+1){
                    // 找到了[1,mx]内的所有整数
                    ans++;
                }
            }
            return ans;
        }
    }
}
