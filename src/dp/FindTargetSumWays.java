package dp;

import java.util.HashMap;

/**
 * 494. 目标和[medium]
 * https://leetcode.cn/problems/target-sum/
 */
public class FindTargetSumWays {
    class Solution {

        // 穷举+备忘录
        public int findTargetSumWaysII(int[] nums, int target) {
            if (nums.length == 0) return 0;
            return dp(nums, 0, target);
        }

        // 备忘录
        HashMap<String, Integer> memo = new HashMap<>();

        // dp(nums,i,target)返回nums[i,..]凑成目标和为target的组合数
        private int dp(int[] nums, int i, int target) {
            // base case
            if (i==nums.length){ // 遍历结束，没有元素
                if (target==0) return 1; // 没有元素目标和为0也是一种凑法
                return 0;
            }
            // 把它俩转成字符串才能作为哈希表的键
            String key = i + "," + target;
            // 避免重复计算
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            // 穷举
            int res=dp(nums,i+1,target-nums[i])+ // +nums[i]
                    dp(nums,i+1,target+nums[i]); // -nums[i]
            // 记入备忘录
            memo.put(key, res);
            return res;
        }


        // 背包问题思路（动态规划）
        public int findTargetSumWays(int[] nums, int target) {
            int n = nums.length;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += nums[i];
            }
            /**
             * 把 nums 划分成两个子集 A 和 B，分别代表分配 + 的数和分配 - 的数
             *  sum(A) - sum(B) = target
             *  sum(A) = target + sum(B)
             *  sum(A) + sum(A) = target + sum(B) + sum(A)
             *  2 * sum(A) = target + sum(nums)
             */
            // 这两种情况，不可能存在合法的子集划分
            if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
                return 0;
            }

            // 找子集，目标和为(sum+target)/2
            target = (sum + target) / 2;

            // dp[i][j]表示使用nums[0,i-1]构造的表达式最终目标和为target的数目
            int[][] dp = new int[n + 1][target + 1];
            // 如果目标和为 0，没有元素也是一种
            dp[0][0]=1;

            // 选择1 元素
            for (int i = 1; i <= n; i++) {
                // 选择2 目标和
                for (int j = 0; j <= target; j++) {
                    if (j >= nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[n][target];
        }
    }
}
