package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 199. 二叉树的右视图[medium]
 * https://leetcode.cn/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class RightSideView {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        System.out.println(solution.rightSideView(root));

    }

    static class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            ArrayList<Integer> res = new ArrayList<>();
            traverse(root, 0, res);
            return res;
        }

        // 先递归右子树，再递归左子树，当某个深度首次到达时，对应的节点就在右视图中
        private void traverse(TreeNode root, int deep, ArrayList<Integer> res) {
            if (root == null) {
                return;
            }
            // 这个深度首次到达
            if (deep == res.size()) {
                res.add(root.val);
            }
            traverse(root.right, deep + 1, res);
            traverse(root.left, deep + 1, res);
        }
    }
}
