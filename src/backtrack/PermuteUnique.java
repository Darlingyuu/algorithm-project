package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 47. 全排列 II[medium]
 * https://leetcode.cn/problems/permutations-ii/
 */
public class PermuteUnique {
    public static void main(String[] args) {
        int[] nums={1,1,2};
//        int[] nums={1,2,3};
        Solution solution = new Solution();
        List<List<Integer>> list = solution.permuteUnique(nums);
        for (List<Integer> integerList : list) {
            for (Integer integer : integerList) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }

    static class Solution {
        List<List<Integer>> res=new ArrayList<>();
        LinkedList<Integer> trace=new LinkedList<>();
        boolean[] status;
        public List<List<Integer>> permuteUnique(int[] nums) {
            //排序，让重复数字排在一起
            Arrays.sort(nums);
            //记录是否访问过
            status=new boolean[nums.length];
            backtrack(nums);
            return res;
        }

        private void backtrack(int[] nums) {
            //结束条件
            if (trace.size()==nums.length){
                res.add(new ArrayList<>(trace));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                //剪枝条件，访问过，或与前一个元素的值相同
                if (status[i] || (i!=0 && nums[i]==nums[i-1] && !status[i-1])){
                    continue;
                }
                //做选择
                trace.add(nums[i]);
                status[i]=true;
                //进入下一层决策树
                backtrack(nums);
                //撤销选择
                trace.removeLast();
                status[i]=false;
            }
        }
    }
}
