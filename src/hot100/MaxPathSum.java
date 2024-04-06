package hot100;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/?envType=study-plan-v2&envId=top-100-liked
 * 124. 二叉树中的最大路径和[hard]
 */
public class MaxPathSum {
    class Solution {
        private int ans = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return ans;

        }

        private int dfs(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 左子树最大链和
            int lVal = dfs(root.left);
            // 右子树最大链和
            int rVal = dfs(root.right);
            // 两条链拼成路径
            ans = Math.max(ans, lVal + rVal + root.val);
            // 当前子树最大链和
            return Math.max(Math.max(lVal, rVal) + root.val, 0);
        }
    }
}
