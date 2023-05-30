package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 1110. 删点成林[medium]
 * https://leetcode.cn/problems/delete-nodes-and-return-forest/
 */
public class DelNodes {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(5);
        TreeNode l6 = new TreeNode(6);
        TreeNode l7 = new TreeNode(7);
        l1.left=l2;
        l1.right=l3;
        l2.left=l4;
        l2.right=l5;
        l3.left=l6;
        l3.right=l7;
        int[] to_delete={3,5};
        Solution solution = new Solution();
        List<TreeNode> list = solution.delNodes(l1, to_delete);
        for (TreeNode treeNode : list) {
            System.out.println(treeNode);
        }
    }

    static class Solution {
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            ArrayList<TreeNode> list = new ArrayList<>();
            ArrayList<Integer> delete = new ArrayList<>();
            for (int i : to_delete) {
                delete.add(i);
            }

            TreeNode node = traverse(root, delete, list);
            if (node!=null) {
                list.add(node);
            }
            return list;
        }

        // 后序位置添加逻辑
        private TreeNode traverse(TreeNode root,ArrayList<Integer> delete, ArrayList<TreeNode> list) {
            if (root==null) return null;

            TreeNode nodeLeft = traverse(root.left, delete, list);
            TreeNode nodeRight = traverse(root.right,delete,list);
            // 判断当前根节点是否需要删除
           if (delete.contains(root.val)){// 要删除
               // 把左右节点放入list中
               if (nodeLeft!=null) {
                   list.add(nodeLeft);
               }
               if (nodeRight!=null) {
                   list.add(nodeRight);
               }
               // 将当前节点置为null
               root=null;
           }  else {
               root.left=nodeLeft;
               root.right=nodeRight;
           }
           return root;
        }
    }
}
