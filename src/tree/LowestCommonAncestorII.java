package tree;

/**
 * 1644.二叉树的最近公共祖先 II[medium]
 * 给你输入一棵不含重复值的二叉树的，以及两个节点p和q，
 * 如果p或q不存在于树中，则返回空指针，否则的话返回p和q的最近公共祖先节点。
 */
public class LowestCommonAncestorII {
    class Solution {
        // 用于记录 p 和 q 是否存在于二叉树中
        boolean foundP = false, foundQ = false;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode res=find(root,p.val,q.val);
            if (!foundP || !foundQ){
                return null;
            }
            // p 和 q 都存在二叉树中，才有公共祖先
            return res;
        }

        private TreeNode find(TreeNode root, int val1, int val2) {
            if (root==null){
                return null;
            }

            TreeNode left = find(root.left, val1, val2);
            TreeNode right = find(root.right, val1, val2);

            // val1 val2不一定都存在于树中，必须在后序位置判断
            // 后序位置，判断当前节点是不是 LCA 节点
            if (left!=null && right!=null){
                return root;
            }

            // 当前节点不是 LCA 节点
            // 后序位置，判断当前节点是不是目标值（此处代码不可放在前序位置，否则当root=val1，val2不存在，LCA应该是null而不是root）
            if (root.val==val1 || root.val==val2){
                // 找到了，记录一下
                if (root.val == val1) foundP = true;
                if (root.val == val2) foundQ = true;
                return root;
            }
            return left!=null?left:right;
        }
    }
}
