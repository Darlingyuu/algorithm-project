package daily;

/**
 * 1373. 二叉搜索子树的最大键值和[hard]
 * https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/
 */
public class MaxSumBST {
    public static void main(String[] args) {
//        TreeNode l1 = new TreeNode(1);
//        TreeNode l2 = new TreeNode(4);
//        TreeNode l3 = new TreeNode(3);
//        TreeNode l4 = new TreeNode(2);
//        TreeNode l5 = new TreeNode(4);
//        TreeNode l6 = new TreeNode(2);
//        TreeNode l7 = new TreeNode(5);
//        TreeNode l8 = new TreeNode(4);
//        TreeNode l9 = new TreeNode(6);
//        l1.left=l2;
//        l1.right=l3;
//        l2.left=l4;
//        l2.right=l5;
//        l3.left=l6;
//        l3.right=l7;
//        l7.left=l8;
//        l7.right=l9;
        TreeNode l1 = new TreeNode(4);
        TreeNode l2 = new TreeNode(3);
        TreeNode l3 = new TreeNode(1);
        TreeNode l4 = new TreeNode(2);
        l1.left=l2;
        l2.left=l3;
        l2.right=l4;

        Solution solution = new Solution();
        System.out.println(solution.maxSumBST(l1));
    }

    static class Solution {
        private int ans=0;
        public int maxSumBST(TreeNode root) {
            traverse(root);
            return ans;
        }

        // {这棵子树的最小节点值，这棵子树的最大节点值，这棵子树的所有节点值之和}
        private int[] traverse(TreeNode root){
            if (root==null) {
                return new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,0};
            }
            // 递归左子树
            int[] left = traverse(root.left);
            // 递归右子树
            int[] right = traverse(root.right);
            int x = root.val;
            //不是二叉搜索树
            if (x<=left[1] || x>=right[0]){
                return new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,0};
            }

            // 这棵子树的所有节点和
            int sum=left[2]+right[2]+x;
            ans=Math.max(sum,ans);
            return new int[]{Math.min(left[0],x),Math.max(right[1],x),sum};
        }

    }

}
