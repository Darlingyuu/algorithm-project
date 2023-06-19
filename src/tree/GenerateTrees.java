package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II[medium]
 * https://leetcode.cn/problems/unique-binary-search-trees-ii/
 */
public class GenerateTrees {
    class Solution {
        public List<TreeNode> generateTrees(int n) {
            if (n == 0) return new LinkedList<>();
            return buildTree(1, n);
        }

        // 节点i,j构建二叉搜索树,返回所有可能
        private List<TreeNode> buildTree(int i, int j) {
            List<TreeNode> res = new LinkedList<>();
            if (i > j) {
                // 空指针
                res.add(null);
                return res;
            }

            // 1、穷举 root 节点的所有可能。
            for (int k = i; k <= j; k++) {
                // 2、递归构造出左右子树的所有合法 BST。
                List<TreeNode> leftList = buildTree(i, k - 1);
                List<TreeNode> rightList = buildTree(k + 1, j);
                // 3、给 root 节点穷举所有左右子树的组合。
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        // k 作为根节点 root 的值
                        TreeNode root = new TreeNode(k);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
            return res;
        }
    }
}
