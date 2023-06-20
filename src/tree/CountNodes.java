package tree;

/**
 * 222. 完全二叉树的节点个数[medium]
 * https://leetcode.cn/problems/count-complete-tree-nodes/
 */
public class CountNodes {
    class Solution {
        public int countNodes(TreeNode root) {
            if (root==null) return 0;

            // 沿最左侧和最右侧分别计算高度(包含root层)
            int leftHeight=0;
            TreeNode left=root;
            while (left!=null){
                left=left.left;
                leftHeight++;

            }
            int rightHeight=0;
            TreeNode right=root;
            while (right!=null){
                right=right.right;
                rightHeight++;
            }
            // 如果左右侧计算的高度相同，则是一棵满二叉树,节点个数=2^n-1
            if (leftHeight==rightHeight){
                return (int)Math.pow(2,leftHeight)-1;
            }

            // 如果左右侧的高度不同，则按照普通二叉树的逻辑计算
            // 对每个子树也会去判断是否是满二叉树，从而降低复杂度
            return 1+countNodes(root.left)+countNodes(root.right);
        }
    }
}
