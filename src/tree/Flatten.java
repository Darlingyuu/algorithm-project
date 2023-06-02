package tree;

/**
 * 114. 二叉树展开为链表[medium]
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 */
public class Flatten {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(5);
        TreeNode l6 = new TreeNode(6);
        l1.left=l2;
        l1.right=l5;
        l2.left=l3;
        l2.right=l4;
        l5.right=l6;
        Solution solution = new Solution();
        solution.flatten(l1);
        System.out.println(l1);
    }

    static class Solution {
        public void flatten(TreeNode root) {
           if (root==null) return;

           // 左子树拉平
            flatten(root.left);
            // 右子树拉平
            flatten(root.right);

            // 后序遍历位置
            TreeNode left = root.left;
            TreeNode right = root.right;

            // 将左子树作为右子树
            root.left=null;
            root.right=left;

            // 将原先的右子树接到当前右子树的末端
            TreeNode node = root;
            while (node.right!=null){
                node=node.right;
            }
            // 结束循环时，node刚好指向当前右子树最后一个节点
            node.right=right;

        }


    }
}
