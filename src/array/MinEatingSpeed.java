package array;

import java.util.Arrays;

/**
 * 875. 爱吃香蕉的珂珂[medium]
 * https://leetcode.cn/problems/koko-eating-bananas/
 */
public class MinEatingSpeed {
    public static void main(String[] args) {
        int[] piles={3,6,7,11};
        Solution solution = new Solution();
        System.out.println(solution.minEatingSpeed(piles, 8));
    }

    static class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            // 每小时最多吃完一堆，最少吃一根
            // 最少为每堆数量最少的
            int min= 1;
            // 最多为每堆数量最多的
            int max= Arrays.stream(piles).max().getAsInt();

            // 二分搜索寻找满足条件的最小值
            // [ } 寻找左边界
            int left=min,right=max+1;
            while (left<right){
                int mid=left+(right-left)/2;
                if (eatingSpeed(piles,mid,h)){ // 收缩右边界
                    right=mid;
                }else {// 收缩左边界
                    left=mid+1;
                }
            }
            return left;
        }

        private boolean eatingSpeed(int[] piles, int mid, int h) {
            int hourSum=0;
            for (int i = 0; i < piles.length; i++) {
                // 吃完当前这堆需要的小时数
                int curHour = piles[i] % mid == 0 ? piles[i] / mid : piles[i] / mid + 1;
                hourSum+=curHour;

            }
            return hourSum<=h;
        }
    }
}
