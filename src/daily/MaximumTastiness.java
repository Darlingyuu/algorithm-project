package daily;

import java.util.Arrays;

/**
 * 2517. 礼盒的最大甜蜜度[medium]
 * https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/
 */
public class MaximumTastiness {
    public static void main(String[] args) {
        int[] price={13,5,1,8,21,2};
        Solution solution = new Solution();
        System.out.println(solution.maximumTastiness(price, 3));
    }

    static class Solution {
        public int maximumTastiness(int[] price, int k) {
            int n = price.length;
            Arrays.sort(price);
            // 最小绝对值差
            int left=0;
            // 最大绝对值差
            int right=price[n-1]-price[0]+1;
            // 使用二分查找
            // [ ) 找到右边界
            while (left<right){
                int mid=left+(right-left)/2;
                if (check(price,mid,k)){ // 有k个元素，他们之间的绝对值差都大于等于mid
                    left=mid+1;
                }else {
                    right=mid;
                }
            }
            return left-1;


        }

        // 判断是否有k个元素，他们之间的绝对值差都大于等于mid
        //price 已经排序过
        private boolean check(int[] price, int mid, int k) {
            int pre=price[0];
            int count=1;
            for (int i = 1; i < price.length; i++) {
                if ( count>=k) return true;
                if (price[i]-pre>=mid){
                    count++;
                    pre=price[i];
                }
            }
            return count>=k;
        }

    }
}
