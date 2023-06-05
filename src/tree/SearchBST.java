package tree;

/**
 * 700. 二叉搜索树中的搜索[easy]
 * https://leetcode.cn/problems/search-in-a-binary-search-tree/
 */
public class SearchBST {

    class Solution {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root==null) return null;

            if (root.val==val){
                return root;
            }else if (root.val<val){
                return searchBST(root.right,val);
            }else if (root.val>val){
                return searchBST(root.left,val);
            }
            return root;
        }
    }
}
