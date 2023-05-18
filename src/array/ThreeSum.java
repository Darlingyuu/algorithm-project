package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和[medium]
 * https://leetcode.cn/problems/3sum/
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums={-1,0,1,2,-1,-4};
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.threeSum(nums);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer+",");
            }
            System.out.println();
        }
    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            //排序
            Arrays.sort(nums);
            List<List<Integer>> res=new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                List<List<Integer>> towSumList = towSum(nums, i + 1, -nums[i]);
                if (towSumList.size()>0){
                    for (List<Integer> towSum : towSumList) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.addAll(towSum);
                        res.add(list);
                    }

                }
                while (i<nums.length-1 && nums[i+1]==nums[i]) i++;
            }
            return res;
        }

        private List<List<Integer>> towSum(int[] nums ,int start, int target){
            List<List<Integer>> res = new ArrayList<>();
            int p=start,q=nums.length-1;
            while (p<q){
                int sum = nums[p] + nums[q];
                int left = nums[p];
                int right = nums[q];
                if (sum<target){
                    while (p<q && left==nums[p]) p++;
                }else if (sum>target){
                    while (p<q && right==nums[q]) q--;
                }else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[p]);
                    list.add(nums[q]);
                    res.add(list);
                    while (p<q && left==nums[p]) p++;
                    while (p<q && right==nums[q]) q--;
                }
            }
            return res;
        }
    }
}
