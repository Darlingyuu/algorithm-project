package hot100;

/**
 * 98. 验证二叉搜索树[medium]
 * https://leetcode.cn/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class IsValidBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode l1 = new TreeNode(4);
        TreeNode l2 = new TreeNode(6);
        l2.left = new TreeNode(3);
        l2.right = new TreeNode(7);
        root.left = l1;
        root.right = l2;
        System.out.println(new Solution().isValidBST(root));
    }

    static class Solution {
        public boolean isValidBST(TreeNode root) {
            // 左小右大
            // root 的整个左子树都要小于 root.val，整个右子树都要大于 root.val。
            return isValidBST(root,null,null);
        }

        public boolean isValidBST(TreeNode root,TreeNode min,TreeNode max) {
            if (root == null) {
                return true;
            }

            // 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val
            if (min!=null&&root.val<=min.val) return false;
            if (max!=null && root.val>=max.val) return false;

            // 检查左右子树，限定左子树的最大值是 root.val，右子树的最小值是 root.val
            return isValidBST(root.left,min,root) &&isValidBST(root.right,root,max);
        }

    }
}
