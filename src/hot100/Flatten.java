package hot100;

/**
 * 114. 二叉树展开为链表[medium]
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Flatten {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        Solution solution = new Solution();
        solution.flatten(root);
        System.out.println(root);
    }

    static class Solution {
        TreeNode node;

        public void flatten(TreeNode root) {
            node = new TreeNode(-1);
            traverse(root);
            node.right = null;
        }

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            node.right = root;
            node = node.right;
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = null;
            root.right = null;
            traverse(left);
            traverse(right);
        }
    }
}
