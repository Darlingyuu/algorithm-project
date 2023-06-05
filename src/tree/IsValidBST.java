package tree;

/**
 * 98. 验证二叉搜索树[medium]
 * https://leetcode.cn/problems/validate-binary-search-tree/
 */
public class IsValidBST {
    class Solution {
        public boolean isValidBST(TreeNode root) {
            // 左小右大
            // root 的整个左子树都要小于 root.val，整个右子树都要大于 root.val。
            return isValidBST(root,null,null);
        }

        /**
         * 左小右大
         *  root 的整个左子树都要小于 root.val，整个右子树都要大于 root.val
         * @param root 当前验证的根节点
         * @param min 右子树不可超过的节点值
         * @param max 左子树不可小于的节点值
         * @return
         */
        private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            // base case
            if (root==null) return true;
            // 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val
            if (min!=null&&root.val<=min.val) return false;
            if (max!=null && root.val>=max.val) return false;

            // 检查左右子树，限定左子树的最大值是 root.val，右子树的最小值是 root.val
            return isValidBST(root.left,min,root) &&isValidBST(root.right,root,max);
        }

    }
}
