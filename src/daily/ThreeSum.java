package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和[medium]
 * https://leetcode.cn/problems/3sum/
 */
public class ThreeSum {
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            // 排序
            Arrays.sort(nums);
            List<List<Integer>> res=new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                // 得到nums[i+1,..]中和为-nums[i]的两个数  集合
                // 每个集合中的两个数加上nums[i]即为结果
                List<List<Integer>> towSum=towSum(nums,i+1,-nums[i]);
                if (towSum.size()>0){
                    for (List<Integer> list : towSum) {
                        ArrayList<Integer> item = new ArrayList<>();
                        item.add(nums[i]);
                        item.addAll(list);
                        res.add(item);
                    }
                }
                // 跳过重复值
                while (i<nums.length-1 && nums[i+1]==nums[i]) i++;
            }
            return res;
        }

        // 得到nums[start,..]中和为target的两个数 集合
        private List<List<Integer>> towSum(int[] nums, int start, int target) {
            List<List<Integer>> res=new ArrayList<>();
            int p=start,q=nums.length-1;
            while (p<q){
                int sum = nums[p] + nums[q];
                int left = nums[p];
                int right = nums[q];
                if (sum<target){
                    // 跳过重复值
                    while (p<q && left==nums[p]) p++;
                }else if (sum>target){
                    // 跳过重复值
                    while (p<q && right==nums[q]) q--;
                }else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[p]);
                    list.add(nums[q]);
                    res.add(list);
                    // 跳过重复值
                    while (p<q && left==nums[p]) p++;
                    while (p<q && right==nums[q]) q--;
                }
            }
            return res;
        }
    }
}
