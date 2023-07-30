package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和[medium]
 * https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked
 */
public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums={-1,0,1,2,-1,-4};
        System.out.println(solution.threeSum(nums));

    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res=new ArrayList<>();
            // 排序，让相等的数排在一块
            Arrays.sort(nums);
            // 遍历每个数
            for (int i = 0; i < nums.length; i++) {
                // 获取和为-nums[i]的两个数的结果
                List<List<Integer>> towSum=twoSum(nums,i+1,-nums[i]);
                if (towSum.size()>0){
                    for (List<Integer> list : towSum) {
                        ArrayList<Integer> item = new ArrayList<>();
                        item.add(nums[i]);
                        item.addAll(list);
                        res.add(item);
                    }
                }

                // 跳过重复数
                while (i<nums.length-1 && nums[i+1]==nums[i]) i++;

            }
            return res;
        }

        // nums[start,...]返回和为target的两个数的集合
        private List<List<Integer>> twoSum(int[] nums, int start, int target) {
            List<List<Integer>> res=new ArrayList<>();
            // 双指针
            int p=start,q=nums.length-1;
            while (p<q){
                int sum = nums[p] + nums[q];
                int left = nums[p];
                int right = nums[q];
                if (sum==target){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(left);
                    list.add(right);
                    res.add(list);
                    // 跳过重复值
                    while (p<q && nums[p]==left) p++;
                    while (p<q && nums[q]==right) q--;
                }else if (sum<target){
                    // 跳过重复值
                    while (p<q && nums[p]==left) p++;
                }else if (sum>target){
                    // 跳过重复值
                    while (p<q && nums[q]==right) q--;
                }
            }
            return res;
        }
    }
}
