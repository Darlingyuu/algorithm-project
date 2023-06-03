package tree;

/**
 * 1038. 从二叉搜索树到更大和树[medium]
 * https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/
 * 538. 把二叉搜索树转换为累加树[medium]
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/description/
 */
public class BstToGst {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(4);
        TreeNode l2 = new TreeNode(1);
        TreeNode l3 = new TreeNode(6);
        TreeNode l4 = new TreeNode(0);
        TreeNode l5 = new TreeNode(2);
        TreeNode l6 = new TreeNode(5);
        TreeNode l7 = new TreeNode(7);
        TreeNode l8 = new TreeNode(3);
        TreeNode l9 = new TreeNode(8);
        l1.left=l2;
        l1.right=l3;
        l2.left=l4;
        l2.right=l5;
        l3.left=l6;
        l3.right=l7;
        l5.right=l8;
        l7.right=l9;
        Solution solution = new Solution();
        System.out.println(solution.bstToGst(l1));

    }

    static class Solution {
        public TreeNode bstToGst(TreeNode root) {
            traverse(root);
            return root;
        }
        // 记录累加和
        int sum=0;
        private void traverse(TreeNode root) {
            if (root==null) return;

            // 遍历右子树
            traverse(root.right);

            // 中序位置
            sum+=root.val;
            root.val=sum;

            // 遍历左子树
            traverse(root.left);
        }
    }
}
