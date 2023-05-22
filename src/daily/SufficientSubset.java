package daily;

/**
 * 1080. 根到叶路径上的不足节点[medium]
 * https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/
 */
public class SufficientSubset {
    public static void main(String[] args) {

    }

    class Solution {

        public TreeNode sufficientSubset(TreeNode root, int limit) {
            boolean rootDeleted=dfs(root,0,limit);
            if (rootDeleted){
                return null;
            }
            return root;
        }

        /**
         *
         * @param node 当前节点
         * @param sum 从根节点到当前节点的值的总和
         * @param limit
         * @return root节点是否要删除
         */
        private boolean dfs(TreeNode node, int sum, int limit) {
            // 结束条件
            // 当前节点为叶子结点,需比较sum和limit大小
            if (node.left==null && node.right==null){
                // 小于limit，则该节点要删除 返回true
                return sum+node.val<limit;
            }

            // 用来标记左右子树是否要删除
            boolean leftTree=true;
            boolean rightTree=true;

            // 从根节点到当前节点的值的总和
            sum+=node.val;

            // 如果存在左子树则递归
            if (node.left!=null){
                leftTree=dfs(node.left,sum,limit);
            }
            //如果存在右子树六递归
            if (node.right!=null){
                rightTree=dfs(node.right,sum,limit);
            }

            //根据左右子树返回的情况来判断是否要删除
            if (leftTree){
                node.left=null;
            }
            if (rightTree){
                node.right=null;
            }

            // 只有左右子树都被删除了，自己才没有保留的必要
            return leftTree&&rightTree;
        }
    }
}
