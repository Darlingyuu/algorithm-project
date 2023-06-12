package tree;

import java.util.HashSet;

/**
 * 1676.二叉树的最近公共祖先 IV[medium]
 * 输入一个包含若干节点的列表nodes（这些节点都存在于二叉树中），让你算这些节点的最近公共祖先
 */
public class LowestCommonAncestorIV {
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            // 将列表转化成哈希集合，便于判断元素是否存在
            HashSet<Integer> values = new HashSet<>();
            for (TreeNode node : nodes) {
                values.add(node.val); //node.val各不相同
            }
            return find(root,values);
        }

        // 在二叉树中寻找 values 的最近公共祖先节点
        private TreeNode find(TreeNode root, HashSet<Integer> values) {
            if (root==null){
                return null;
            }

            // 前序位置
            if (values.contains(root.val)){
                return root;
            }

            TreeNode left = find(root.left, values);
            TreeNode right = find(root.right, values);

            // 后序位置,已经知道左右子树是否存在目标值
            if (left!=null && right!=null){
                // 当前节点是 LCA 节点
                return root;
            }

            return left!=null?left:right;
        }
    }
}
