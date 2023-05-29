package array;

import java.util.Arrays;

/**
 * 1011. 在 D 天内送达包裹的能力[medium]
 * https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/
 */
public class ShipWithinDays {
    public static void main(String[] args) {
        int[] weight = {1,2,3,4,5,6,7,8,9,10};
        Solution solution = new Solution();
        System.out.println(solution.shipWithinDays(weight, 1));
    }

    static class Solution {
        public int shipWithinDays(int[] weights, int days) {
            int length = weights.length;
            // 包括不能拆分，得到最小运载为最重的包裹，最大运载为包裹重量之和
            int min= Arrays.stream(weights).max().getAsInt();
            int max= Arrays.stream(weights).sum();

            int left = min;
            int right = max + 1;
            // [ )
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (ship(mid, weights, days)) { // mid天可以满足，继续寻找左边界
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;

        }

        private boolean ship(int mid, int[] weights, int days) {
            int sum = 0;
            int day = 1;
            for (int i = 0; i < weights.length; i++) {
                if (sum+weights[i]>mid){
                    day++;
                    sum=weights[i];
                }else {
                    sum+=weights[i];
                }
            }
            return day<=days;
        }
    }
}
