package daily;

/**
 * 617. 合并二叉树[easy]
 * https://leetcode.cn/problems/merge-two-binary-trees/
 */
public class MergeTrees {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.left.right = new TreeNode(4);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(7);
        System.out.println(solution.mergeTrees(root1, root2));
    }

    static class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }

            TreeNode root = new TreeNode(root1.val + root2.val);
            root.left = mergeTrees(root1.left, root2.left);
            root.right = mergeTrees(root1.right, root2.right);
            return root;
        }
    }
}
