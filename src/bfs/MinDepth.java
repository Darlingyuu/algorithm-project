package bfs;

import java.util.LinkedList;

/**
 * 111. 二叉树的最小深度[easy]
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 */
public class MinDepth {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(5);
        l1.left=l2;
        l1.right=l3;
        l2.left=l4;
        l2.right=l5;
        Solution solution = new Solution();
        System.out.println(solution.minDepth(l1));
    }

    static class Solution {
        public int minDepth(TreeNode root) {
            if (root==null) return 0;
            //存放一层节点
            LinkedList<TreeNode> queue = new LinkedList<>();
            //根节点就是一层，深度为1
            int depth=1;
            //放入起点
            queue.offer(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                // 遍历当前层
                // 此处不能在size出直接用queue.size()，否则会受到循环内部queue.offer()的影响
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    //判断是否到达终点
                    if (cur.left==null && cur.right==null){
                        return depth;
                    }
                    //扩散，将左右节点都放队列中
                    if (cur.left!=null){
                        queue.offer(cur.left);
                    }
                    if (cur.right!=null){
                        queue.offer(cur.right);
                    }
                }
                //深度增加
                depth++;
            }
            return depth;
        }
    }
}
