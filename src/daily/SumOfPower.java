package daily;

import java.util.Arrays;

/**
 * 2681. 英雄的力量[hard]
 * https://leetcode.cn/problems/power-of-heroes/
 */
public class SumOfPower {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums={658,489,777,2418,1893,130,2448,178,1128,2149,1059,1495,1166,608,2006,713,
                1906,2108,680,1348,860,1620,146,2447,1895,1083,1465,2351,1359,1187,906,533,1943,
                1814,1808,2065,1744,254,1988,1889,1206};
        System.out.println(solution.sumOfPower(nums));
    }
    static class Solution {
        public int sumOfPower(int[] nums) {
            final int mod = (int) 1e9 + 7;
            Arrays.sort(nums);
            long ans = 0, p = 0;
            // https://leetcode.cn/problems/power-of-heroes/solutions/2367175/python3javacgotypescript-yi-ti-yi-jie-pa-lgos/
            for (int i = nums.length - 1; i >= 0; --i) {
                long x = nums[i];
                ans = (ans + (x * x % mod) * x) % mod;
                ans = (ans + x * p % mod) % mod;
                p = (p * 2 + x * x % mod) % mod;
            }
            return (int) ans;
        }
    }
}
