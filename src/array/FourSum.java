package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和[medium]
 * https://leetcode.cn/problems/4sum/
 */
public class FourSum {
    public static void main(String[] args) {
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.fourSum(nums, -294967296);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer+",");
            }
            System.out.println();
        }
    }

    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return nSum(nums,4,0,target);
        }

        private List<List<Integer>> nSum(int[] nums ,int n,int start, long target){
            List<List<Integer>> res = new ArrayList<>();
            if (n==2) {
                int p = start, q = nums.length - 1;
                while (p < q) {
                    int sum = nums[p] + nums[q];
                    int left = nums[p];
                    int right = nums[q];
                    if (sum < target) {
                        while (p < q && left == nums[p]) p++;
                    } else if (sum > target) {
                        while (p < q && right == nums[q]) q--;
                    } else {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[p]);
                        list.add(nums[q]);
                        res.add(list);
                        while (p < q && left == nums[p]) p++;
                        while (p < q && right == nums[q]) q--;
                    }
                }
            }else {
                for (int i = start; i < nums.length; i++) {
                    List<List<Integer>> nSum = nSum(nums, n-1, i + 1, target - nums[i]);
                    if (nSum.size()>0){
                        for (List<Integer> integers : nSum) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.addAll(integers);
                            res.add(list);
                        }
                    }
                    while (i<nums.length-1 && nums[i]==nums[i+1]) i++;
                }
            }
            return res;
        }
    }
}
