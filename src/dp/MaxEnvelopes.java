package dp;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题[hard]
 * https://leetcode.cn/problems/russian-doll-envelopes/
 */
public class MaxEnvelopes {
    public static void main(String[] args) {
        int[][] envelopes={{4,5},{4,6},{6,7},{2,3},{1,1}};
        Solution solution = new Solution();
        System.out.println(solution.maxEnvelopes(envelopes));
    }

    static class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            // 按照宽度升序排序，宽度相同时按照高度降序排序
            Arrays.sort(envelopes, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });

            // 使用二叉搜索（扑克牌）
            int[] top = new int[envelopes.length];
            int piles=0;
            for (int i = 0; i < envelopes.length; i++) {
                int num = envelopes[i][1];

                // num的位置 [ ) 寻找左边界
                int left=0,right=piles;
                while (left<right){
                    int mid=left+(right-left)/2;
                    if (top[mid]==num){
                        right=mid;
                    }else if (top[mid]>num){
                        right=mid;
                    }else if (top[mid]<num){
                        left=mid+1;
                    }
                }

                // 没有找到合适位置，堆数+1
                if (left==piles) piles++;
                // 找到或放在新堆上
                top[left]=num;
            }
            return piles;
        }

        // 复杂的实例，超出时间限制
//        public int maxEnvelopes(int[][] envelopes) {
//            // 按照宽度升序排序，宽度相同时按照高度降序排序
//            Arrays.sort(envelopes,(a,b)->{
//                if (a[0]==b[0]){
//                    return b[1]-a[1];
//                }
//                return a[0]-b[0];
//            });
//
//            // 排序后，保证宽度上是递增序列
//            // 现在只需在高度上寻找（宽度相同高度降序，从而避免在高度是哪个寻找递增序列时遇到相同高度的）
//            int[] dp = new int[envelopes.length];
//            Arrays.fill(dp,1);
//            for (int i = 0; i < envelopes.length; i++) {
//                for (int j = 0; j < i; j++) {
//                    if (envelopes[i][1]>envelopes[j][1]){
//                        dp[i]=Math.max(dp[i],dp[j]+1);
//                    }
//                }
//            }
//            // 最终结果（子序列的最大长度）应该是 dp 数组中的最大值
//            int res=0;
//            for (int i = 0; i < dp.length; i++) {
//                res=Math.max(res,dp[i]);
//            }
//            return res;
//        }
    }
}
