package tree;

/**
 * 450. 删除二叉搜索树中的节点[medium]
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 */
public class DeleteNode {
    public static void main(String[] args) {

    }

    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root==null) return null;

            if (root.val==key){
                // 找到了，删除
                // 情况1： 刚好是叶子结点  return null
                // 情况2：只有一个非空子节点，需要该子节点接替root位置
                if (root.left==null) return root.right;
                if (root.right==null) return root.left;

                // 情况3：有两个子节点，
                // 为了不破坏 BST 的性质，必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己
                // 此处采用右子树中最小的那个节点来接替自己
                if (root.left!=null && root.right!=null){
                    // 找到右子树的最小节点
                    TreeNode minNode=getMin(root.right);
                    // 删除右子树最小的节点
                    root.right= deleteNode(root.right,minNode.val);
                    // 用右子树最小节点替换root
                    minNode.left=root.left;
                    minNode.right=root.right;
                    root=minNode;
                }

            }else if (root.val>key){
                // 去左子树找
                root.left=deleteNode(root.left,key);
            }else if (root.val<key){
                // 去右子树找
                root.right=deleteNode(root.right,key);
            }
            return root;
        }

        // 返回最小节点
        private TreeNode getMin(TreeNode node) {
            while (node.left!=null){
                node=node.left;
            }
            return node;
        }
    }
}
