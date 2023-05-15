package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合[medium]
 * https://leetcode.cn/problems/combinations/
 */
public class Combine {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<List<Integer>> list = solution.combine(4, 2);
        List<List<Integer>> list = solution.combine(1, 1);
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
        public List<List<Integer>> combine(int n, int k) {
            backtrack(n,k,1);
            return res;
        }

        private void backtrack(int n, int k, int start) {
            //结束条件
            if (trace.size()==k){
                res.add(new ArrayList<>(trace));
                return;
            }
            if (trace.size()>k) return;

            for (int i = start; i <= n; i++) {
                //做选择
                trace.add(i);
                //进入下一层决策树
                backtrack(n,k,i+1);
                //撤销选择
                trace.removeLast();
            }
        }
    }
}
