package tree;

/**
 * 889. 根据前序和后序遍历构造二叉树[medium]
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 */
public class ConstructFromPrePost {
    public static void main(String[] args) {
        int[] preorder={2,1};
        int[] postorder={1,2};
        Solution solution = new Solution();
        System.out.println(solution.constructFromPrePost(preorder, postorder));
    }

    static class Solution {
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            return build(preorder,0,postorder.length-1,postorder,0,postorder.length-1);
        }

        private TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
                // 结束条件
            if (preStart==preEnd) return new TreeNode(preorder[preStart]);
            if (preStart>preEnd) return null;
                // 根节点
                TreeNode root = new TreeNode(preorder[preStart]);
                // 直接将前序的下一个当做左节点
                int leftVal = preorder[preStart + 1];
                // 该节点在后序中的位置
                int index = getIndex(leftVal, postorder);
                // leftVal的左子树大小
                int leftSize =  index-postStart+1;
                // 构建左子树
                // preorder[preStart+1, preStart+1+leftSize-1]
                // postorder[postStart,index]
                root.left=build(preorder, preStart+1, preStart+leftSize, postorder, postStart,index);
                // 构建右子树
                // preorder[preStart+leftSize+1, preEnd]
                // postorder[index+1,postEnd-1]
                root.right=build(preorder, preStart+leftSize+1, preEnd, postorder, index+1,postEnd-1);

                return root;

        }
        private int getIndex(int rootVal, int[] postorder) {
            int index=-1;
            for (int i = 0; i < postorder.length; i++) {
                if (rootVal==postorder[i]){
                    index=i;
                    break;
                }
            }
            return index;
        }
    }
}
