package tree;

/**
 * 236. 二叉树的最近公共祖先[medium]
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestor {
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root==null) return null;

            // 前序位置
            if (root==p || root==q) {
                // 如果遇到目标值，直接返回
                // 此处也能覆盖root就是最近公共祖先的情况，另一节点肯定在root的子树中，无需继续遍历
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            // 分别在左右子树找到节点p q，那么当前节点就是最近公共祖先
            if (left!=null && right!=null){
                return root;
            }

            return left!=null?left:right;
        }
    }
}
