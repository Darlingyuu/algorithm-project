package hot100;

/**
 * https://leetcode.cn/problems/symmetric-tree/?envType=study-plan-v2&envId=top-100-liked
 * 101. 对称二叉树[easy]
 */
public class IsSymmetric {
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isEqual(root.left, root.right);
        }


        public boolean isEqual(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if ((p == null && q != null) || (p != null && q == null) || (p.val != q.val)) {
                return false;
            }
            return isEqual(p.left, q.right) && isEqual(p.right, q.left);
        }

    }
}
