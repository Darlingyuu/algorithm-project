package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 216. 组合总和 III[medium]
 * https://leetcode.cn/problems/combination-sum-iii/
 */
public class CombinationSum3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> list = solution.combinationSum3(4, 1);
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
        public List<List<Integer>> combinationSum3(int k, int n) {
            backtrack(k,n,1);
            return res;
        }

        private void backtrack(int k, int n,int start) {
            //结束条件
            if (sum==n && trace.size()==k){
                res.add(new ArrayList<>(trace));
                return;
            }
            if (sum>n || trace.size()>k) return;

            for (int i = start; i <= 9; i++) {
                // 做选择
                trace.add(i);
                sum+=i;//记录总和
                //进入下一层决策树，由start控制不产生重复元素
                backtrack(k,n,i+1);
                //撤销选择
                trace.removeLast();
                sum-=i;
            }
        }
    }
}
