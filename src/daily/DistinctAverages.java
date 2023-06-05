package daily;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 2465. 不同的平均值数目[easy]
 * https://leetcode.cn/problems/number-of-distinct-averages/
 */
public class DistinctAverages {
    public static void main(String[] args) {
        int[] nums={4,1,4,0,3,5};
        Solution solution = new Solution();
        System.out.println(solution.distinctAverages(nums));
    }

    static class Solution {
        public int distinctAverages(int[] nums) {
            HashSet<Integer> set = new HashSet<>();

            Arrays.sort(nums);
            int p=0,q=nums.length-1;
            while (p<q){
                set.add(nums[p++]+nums[q--]); // 和不同，平均数也不同
            }

            return set.size();
        }
    }
}
