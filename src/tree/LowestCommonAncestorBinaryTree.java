package tree;

/**
 * 235. 二叉搜索树的最近公共祖先[medium]
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorBinaryTree {
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 保证 val1 较小，val2 较大
            int val1 = Math.min(p.val, q.val);
            int val2 = Math.max(p.val, q.val);
            return find(root, val1, val2);
        }

        private TreeNode find(TreeNode root, int val1, int val2) {
            if (root==null) return null;

           if (root.val>val2){
               // 当前节点太大，去左子树找
               return find(root.left, val1, val2);
           }
            if (root.val < val1) {
                // 当前节点太小，去右子树找
                return find(root.right, val1, val2);
            }
            // val1 <= root.val <= val2
            // 则当前节点就是最近公共祖先
            return root;
        }
    }
}
