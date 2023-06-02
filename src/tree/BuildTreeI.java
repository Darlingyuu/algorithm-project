package tree;

/**
 * 105. 从前序与中序遍历序列构造二叉树[medium]
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class BuildTreeI {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        Solution solution = new Solution();
        System.out.println(solution.buildTree(preorder, inorder));
    }

    static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
        }

        private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart>preEnd) return null;

            // root节点就是前序遍历数组的第一个元素
            int rootVal = preorder[preStart];
            // rootVal在中序中的下标
            int index=getIndex(rootVal,inorder);
            // 中序中index以左就是左子树范围
            int leftSize = index - inStart;

            // 构造当前根节点
            TreeNode root = new TreeNode(rootVal);
            // 构造左子树
            // preOrder [preStart+1,preStart+leftSize]
            // inOrder [inStart,index-1]
            root.left=build(preorder,preStart+1,preStart+leftSize,
                            inorder,inStart,index-1);
            // 构造右子树
            // preOrder [preStart+leftSize+1,preEnd]
            // inOrder [index+1,inEnd]
            root.right=build(preorder,preStart+leftSize+1,preEnd,
                    inorder,index+1,inEnd);
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
