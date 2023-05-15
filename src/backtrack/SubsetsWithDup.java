package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II[medium]
 * https://leetcode.cn/problems/subsets-ii/
 */
public class SubsetsWithDup {
    public static void main(String[] args) {
        int[] nums={1,2,2};
        Solution solution = new Solution();
        List<List<Integer>> list = solution.subsetsWithDup(nums);
        for (List<Integer> integerList : list) {
            for (Integer integer : integerList) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }

    static class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        public List<List<Integer>> subsetsWithDup(int[] nums) {
           //排序，让重复元素排一起
            Arrays.sort(nums);
            //[]
            res.add(trace);
            backtrack(nums,0);
            return res;
        }

        private void backtrack(int[] nums, int start) {
            //结束条件
            if (start==nums.length) return;

            for (int i = start; i < nums.length; i++) {
                //剪枝条件,处理重复元素
                if (i>start && nums[i]==nums[i-1]) continue;

                //做选择
                trace.add(nums[i]);
                res.add(new ArrayList<>(trace));
                //进入下一层决策树
                backtrack(nums,i+1);
                //撤销选择
                trace.removeLast();
            }
        }
    }
}
