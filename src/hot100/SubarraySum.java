package hot100;

import java.util.HashMap;

/**
 * 560. 和为 K 的子数组[medium]
 * https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&envId=top-100-liked
 */
public class SubarraySum {
    public static void main(String[] args) {
        int[] nums={-1,-1,1};
        Solution solution = new Solution();
        System.out.println(solution.subarraySum(nums, 0));
    }

    static class Solution {
        public int subarraySum(int[] nums, int k) {
            // pre记录[0,i]的总和
            int count = 0, pre = 0;
            // key=和 value=和出现的次数
            HashMap< Integer, Integer > mp = new HashMap <> ();
            // 计算前缀和，初始化一个
            mp.put(0,1);

            for (int i = 0; i < nums.length; i++) {
                // pre记录[0,i]的总和
                pre+=nums[i];

                // 判断是否存在前缀和与pre差值为k
                // 若存在，说明该前缀和对应下标的后一位j [j,i]中间的和为k
                if (mp.containsKey(pre-k)){
                    count+=mp.get(pre-k);
                }

                // 记录前缀和及次数
                mp.put(pre,mp.getOrDefault(pre,0)+1);
            }

            return count;
        }

        public int subarraySumII(int[] nums, int k) {
            int count = 0;
            for (int start = 0; start < nums.length; ++start) {
                int sum = 0;
                for (int end = start; end >= 0; --end) {
                    sum += nums[end];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
