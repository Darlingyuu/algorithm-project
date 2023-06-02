package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 652. 寻找重复的子树[medium]
 * https://leetcode.cn/problems/find-duplicate-subtrees/
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {

    }

    class Solution {
        // 记录所有子树及出现的次数
        private HashMap<String, Integer> memo = new HashMap<>();
        // 记录重复的子树的根节点
        private LinkedList<TreeNode> res = new LinkedList<>();
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
             traverse(root);
             return res;
        }

        // 对root所在的树进行序列化,返回序列化之后的字符串  left + "," + right + "," + root.val
        private String traverse(TreeNode root) {
            if (root==null) return "#";

            String left = traverse(root.left);
            String right = traverse(root.right);
            // 后序位置
            String subTree = left + "," + right + "," + root.val;
            // 判断该子树是否出现次数
            Integer freq  = memo.getOrDefault(subTree, 0);
            if (freq == 1){ // 出现过一次，这次是重复的
                // 多次重复也只会被加入结果集一次
                res.add(root);
            }
            // 将这次出现次数加上
            memo.put(subTree,freq+1);
            return subTree;
        }

    }
}
