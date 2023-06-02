package tree;

/**
 * 106. 从中序与后序遍历序列构造二叉树[medium]
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class BuildTreeII {
    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        Solution solution = new Solution();
        System.out.println(solution.buildTree(inorder, postorder));

    }
    static class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
        }

        private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
            // 结束条件
            if (postStart>postEnd) return null;

            // 后序遍历数组最后一个就是root
            int rootVal = postorder[postEnd];
            // 得到rootVal在中序中的下标
            int index = getIndex(rootVal, inorder);
            // 左子树大小
            int leftSize = index - inStart;

            // 构建根节点
            TreeNode root = new TreeNode(rootVal);
            // 构建左子树
            // inorder [inStart,index-1]
            // postStart [postStart,postStart+leftSize-1]
            root.left=build(inorder,inStart,index-1,
                                postorder,postStart,postStart+leftSize-1);
            // 构建右子树
            // inorder [index+1,inEnd]
            // postStart [postStart+leftSize,postEnd-1]
            root.right=build(inorder,index+1,inEnd,
                    postorder,postStart+leftSize,postEnd-1);
            return root;
        }

        private int getIndex(int rootVal, int[] inorder) {
            int index=-1;
            for (int i = 0; i < inorder.length; i++) {
                if (rootVal==inorder[i]){
                    index=i;
                    break;
                }
            }
            return index;
        }
    }
}
