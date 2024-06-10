package hot100;

/**
 * 230. 二叉搜索树中第K小的元素[medium]
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class KthSmallest {
    class Solution {
        int k;
        int res;

        public int kthSmallest(TreeNode root, int k) {
            this.k = k;
            traverse(root);
            return res;
        }

        private void traverse(TreeNode root) {
            // 到达叶子节点
            if (root == null) {
                return;
            }

            traverse(root.left);
            // 中序位置
            k--;
            if (k == 0) {
                // 当前根节点就是第k小的节点
                res = root.val;
                return;
            }
            traverse(root.right);
        }
    }
}
