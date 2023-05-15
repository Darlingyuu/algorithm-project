package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II[medium]
 * https://leetcode.cn/problems/combination-sum-ii/
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates={10,1,2,7,6,1,5};
        List<List<Integer>> list = solution.combinationSum2(candidates, 8);
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
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            //排序，让重复数字排在一起
            Arrays.sort(candidates);
            backtrack(candidates,target,0);
            return res;
        }

        private void backtrack(int[] candidates, int target, int start) {
            //结束条件
            if (sum==target){
                res.add(new ArrayList<>(trace));
                return;
            }
            if (sum>target) return;

            for (int i = start; i < candidates.length; i++) {
                //// 剪枝逻辑，值相同的树枝，只遍历第一条
                if (i>start && candidates[i]==candidates[i-1]) continue;
                //做选择
                trace.add(candidates[i]);
                sum+=candidates[i];
                //下一层决策树，start控制，不能重复使用元素
                backtrack(candidates,target,i+1);
                //撤销选择
                trace.removeLast();
                sum-=candidates[i];
            }
        }
    }
}
