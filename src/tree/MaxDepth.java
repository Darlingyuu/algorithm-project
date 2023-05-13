package tree;

/**
 * 104. 二叉树的最大深度[easy]
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class MaxDepth {
    public static void main(String[] args) {

    }

    class Solution {
        public int maxDepth(TreeNode root) {
            if (root==null) return 0;
            // 左子树最大深度
            int leftMax = maxDepth(root.left);
            //右子树最大深度
            int rightMax = maxDepth(root.right);
            //当前节点最大深度
            return Math.max(leftMax,rightMax)+1;
        }
    }
}
