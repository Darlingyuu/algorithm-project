package hot100;

/**
 * https://leetcode.cn/problems/invert-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 * 226. 翻转二叉树[easy]
 */
public class InvertTree {
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            TreeNode newRight = invertTree(root.left);
            TreeNode newLeft = invertTree(root.right);
            root.right = newRight;
            root.left = newLeft;
            return root;
        }
    }
}
