package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历[easy]
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 */
public class PreorderTraversal {
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res=new ArrayList<>();
            traversal(root,res);
            return res;
        }

        private void traversal(TreeNode root,List<Integer> res){
            if(root==null) return;
            res.add(root.val);
            traversal(root.left,res);
            traversal(root.right,res);
        }
    }
}
