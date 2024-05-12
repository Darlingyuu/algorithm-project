package hot100;

/**
 * 543. 二叉树的直径[easy]
 * https://leetcode.cn/problems/diameter-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class DiameterOfBinaryTree {

    class Solution {
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            dfs(root);
            return maxDiameter;
        }

        // 返回以root为根节点的单边最长
        public int dfs(TreeNode root) {
            if (root.left == null && root.right == null) {
                return 0;
            }
            // 左子树单边最长
            int left = root.left == null ? 0 : dfs(root.left) + 1;
            // 右子树单边最长
            int right = root.right == null ? 0 : dfs(root.right) + 1;
            // 记录最长路径
            maxDiameter = Math.max(maxDiameter, left + right);
            return Math.max(left, right);
        }
    }
}
