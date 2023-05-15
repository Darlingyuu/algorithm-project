package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和[medium]
 * https://leetcode.cn/problems/combination-sum/
 */
public class CombinationSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates={2,3,5};
        List<List<Integer>> list = solution.combinationSum(candidates, 8);
        for (List<Integer> integerList : list) {
            for (Integer integer : integerList) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }

    static class Solution {
        List<List<Integer>> res=new ArrayList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        int sum=0;
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtrack(candidates,target,0);
            return res;
        }

        private void backtrack(int[] candidates, int target,int start) {
            //结束条件
            if (sum==target){
                res.add(new ArrayList<>(trace));
                return;
            }
            if (sum>target) return;

            for (int i = start; i < candidates.length; i++) {
                //做选择
                trace.add(candidates[i]);
                sum+=candidates[i];
                //进入下一层决策树,同一数字可以重复选取
                backtrack(candidates,target,i);
                //撤回选择
                trace.removeLast();
                sum-=candidates[i];
            }
        }
    }
}
