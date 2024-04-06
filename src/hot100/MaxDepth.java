package hot100;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 * 104. 二叉树的最大深度[easy]
 */
public class MaxDepth {
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return left > right ? left + 1 : right + 1;
        }
    }
}
