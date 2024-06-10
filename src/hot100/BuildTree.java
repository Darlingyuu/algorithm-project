package hot100;

/**
 * 105. 从前序与中序遍历序列构造二叉树[medium]
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class BuildTree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder={1,2};
        int[] inorder={2,1};
        System.out.println(solution.buildTree(preorder, inorder));
    }

    static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode buildTree(int[] preorder, int p, int q, int[] inorder, int m, int n) {
            if (p > q) {
                return null;
            }
            // 根节点
            int val = preorder[p];
            TreeNode root = new TreeNode(val);
            // 查询该节点在中序遍历中的位置
            int index = m;
            for (int i = m; i <= n; i++) {
                if (inorder[i] == val) {
                    index = i;
                    break;
                }
            }
            // inorder中[m,index-1]为左子树，[index+1,n]为右子树
            // 左子树大小index-m,右子树大小n-index
            // preorder左子树范围[p+1,p+index-m],右子树[p+index-m+1,q]
            root.left = buildTree(preorder, p + 1, p + index - m, inorder, m, index - 1);
            root.right = buildTree(preorder, p + index - m + 1, q, inorder, index + 1, n);
            return root;
        }
    }
}
