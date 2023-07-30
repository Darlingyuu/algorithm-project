package hot100;

import java.util.HashSet;

/**
 * 128. 最长连续序列[medium]
 * https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums={1,2,0,1};
        System.out.println(solution.longestConsecutive(nums));
    }

    static class Solution {
        public int longestConsecutive(int[] nums) {
            // 使用set去重
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            // 记录连续序列最长长度
            int longest=0;
            for (Integer num : set) {
                // 不存在num-1才遍历，避免重复
                if (!set.contains(num-1)){
                    // 不存在num-1,则可以将num视为序列起点
                    int cur=num;
                    int curLong=1;

                    // 一直往后面找数字，获取最长长度
                    while (set.contains(cur+1)){
                        cur+=1;
                        curLong+=1;
                    }

                    // 连续序列结束，记录连续序列最长长度
                    longest=Math.max(longest,curLong);
                }
            }
            return longest;
        }
    }
}
