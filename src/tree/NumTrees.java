package tree;

/**
 * 96. 不同的二叉搜索树[medium]
 * https://leetcode.cn/problems/unique-binary-search-trees/
 */
public class NumTrees {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numTrees(3));
    }
    static class Solution {
        int[][] memo;
        public int numTrees(int n) {
            memo=new int[n+1][n+1];
            return buildTree(1,n);
        }

        // 节点i到节点j能够组成的二叉搜索树的个数
        private int buildTree(int i,int j){
            // 空节点null
            if (i>j){
                return 1;
            }

            // 之前计算过
            if (memo[i][j]!=0){
                return memo[i][j];
            }

            int res=0;
            for (int k = i; k <=j ; k++) {
                // k作为根节点
                int left = buildTree(i, k - 1);
                int right = buildTree(k+1, j);
                res+=left*right;
            }
            memo[i][j]=res;
            return res;
        }
    }
}
