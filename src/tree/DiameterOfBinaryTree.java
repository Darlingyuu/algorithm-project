package tree;

/**
 * 543. 二叉树的直径[easy]
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(5);
        l1.left = l2;
        l1.right = l3;
        l2.left = l4;
        l2.right = l5;
        Solution solution = new Solution();
        System.out.println(solution.diameterOfBinaryTree(l1));
    }


    static class Solution {
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return maxDiameter;
        }

        private int maxDepth(TreeNode root) {
            if (root == null) return 0;
            //左子树最大深度
            int leftMax = maxDepth(root.left);
            //右子树最大深度
            int rightMax = maxDepth(root.right);
            // 计算最大直径
            maxDiameter = Math.max(leftMax + rightMax, maxDiameter);
            return Math.max(leftMax, rightMax) + 1;
        }
    }
}
