package hot100;

/**
 * 236. 二叉树的最近公共祖先[medium]
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class LowestCommonAncestor {
    class Solution {
        // 在root为根节点的树上查找p,q的最近公共祖先
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            // 前序位置
            // root最近公共祖先
            if (root == p || root == q) {
                return root;
            }

            // 往左子树查找
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            // 往右子树查找
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            // 若p,q在一侧子树上，那另一个返回值为null
            // 都不为null，说明分别存在左右子树，那么当前节点就是最近公共祖先
            if (left != null && right != null) {
                return root;
            }
            // 迭代到最后且只存在一侧子树，则为最近公共祖先
            return left != null ? left : right;
        }
    }
}
