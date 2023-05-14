package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列[medium]
 * https://leetcode.cn/problems/permutations/
 */
public class Permute {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        Solution solution = new Solution();
        List<List<Integer>> list = solution.permute(nums);
        for (List<Integer> integerList : list) {
            for (Integer integer : integerList) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }

    static class Solution {
        ArrayList res = new ArrayList<LinkedList<Integer>>();
        boolean[] status;

        public List<List<Integer>> permute(int[] nums) {
            // 「路径」中的元素会被标记为 true，避免重复使用
            status=new boolean[nums.length];
            // 记录路径
            LinkedList<Integer> trace = new LinkedList<>();
            backtrack(nums,trace);
            return res;
        }

        private void backtrack(int[] nums,LinkedList<Integer> trace) {
            //结束条件
            if (trace.size()==nums.length){
                res.add(new LinkedList<Integer>(trace));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                // 排除不合法的选择
                if (status[i]) continue;
                //做选择
                trace.add(nums[i]);
                status[i]=true;//标记为已访问
                //下一次决策
                backtrack(nums,trace);
                //撤销决策
                trace.removeLast();
                status[i]=false;//标记为未访问
            }
        }
    }

}
