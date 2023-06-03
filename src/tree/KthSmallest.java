package tree;

/**
 * 230. 二叉搜索树中第K小的元素[medium]
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallest {
    public static void main(String[] args) {

    }

    class Solution {
        int k;
        int res;
        public int kthSmallest(TreeNode root, int k) {
            this.k=k;
            traverse(root);
            return res;
        }

        private void traverse(TreeNode root) {
            if (root==null) return;

            traverse(root.left);
            // 中序位置
            k--;
            if (k==0) {
                res=root.val;
                return;
            }
            traverse(root.right);
        }

    }
}