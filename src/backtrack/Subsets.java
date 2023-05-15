package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集[medium]
 * https://leetcode.cn/problems/subsets/
 */
public class Subsets {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        Solution solution = new Solution();
        List<List<Integer>> list = solution.subsets(nums);
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
        public List<List<Integer>> subsets(int[] nums) {
            //[]
            res.add(trace);
            backtrack(nums,0);
            return res;
        }

        private void backtrack(int[] nums, int start) {
            //结束条件
            if (start==nums.length){
                return;
            }

            for (int i = start; i < nums.length; i++) {
                //做选择
                trace.add(nums[i]);
                res.add(new ArrayList<>(trace));
                //进入下一层决策树，元素不可重复
                backtrack(nums,i+1);
                //撤销选择
                trace.removeLast();
            }
        }
    }
}
