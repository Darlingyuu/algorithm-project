package dp;

import java.util.Arrays;

/**
 * 300. 最长递增子序列[medium]
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class LengthOfLIS {
    public static void main(String[] args) {

    }

    class Solution {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            // base case：dp 数组全都初始化为 1
            Arrays.fill(dp,1);
            for (int i = 0; i < nums.length; i++) {
                // 找到前面那些结尾比nums[i]小的子序列，然后把nums[i]接到这些子序列末尾，就可以形成一个新的递增子序列
                // 而且这个新的子序列长度加一
                for (int j = 0; j < i; j++) {
                    if (nums[i]>nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            // 最终结果（子序列的最大长度）应该是 dp 数组中的最大值
            int res=0;
            for (int i = 0; i < dp.length; i++) {
                res=Math.max(res,dp[i]);
            }
            return res;
        }

        // 二分查找解法
        public int lengthOfLISII(int[] nums) {
            int[] top = new int[nums.length];
            // 初始
            int piles=0;
            for (int i = 0; i < nums.length; i++) {
                // 要处理的扑克牌
                int poker = nums[i];

                // 搜索左侧边界
                int left=0,right=piles;
                while (left<right){
                    int mid=left+(right-left)/2;
                    if (top[mid]>poker){
                        right=mid;
                    }else if (top[mid] < poker) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 没找到合适的牌堆，新建一堆
                if (left == piles) piles++;
                // 把这张牌放到牌堆顶
                top[left] = poker;
            }
            // 牌堆数就是 LIS 长度
            return piles;
        }
    }
}
