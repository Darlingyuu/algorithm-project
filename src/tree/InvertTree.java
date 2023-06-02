package tree;

/**
 * 226. 翻转二叉树[easy]
 * https://leetcode.cn/problems/invert-binary-tree/
 */
public class InvertTree {
    public static void main(String[] args) {

    }

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root==null){
                return root;
            }

            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            root.left=right;
            root.right=left;
            return root;
        }
    }
}
