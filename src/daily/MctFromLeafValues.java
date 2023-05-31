package daily;

/**
 * 1130. 叶值的最小代价生成树[medium]
 * https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/
 */
public class MctFromLeafValues {
    public static void main(String[] args) {

    }

    class Solution {

        /**
         * arr中的值与树的中序遍历中每个叶子节点的值一一对应，可以将数组划分为左右两个非空子数组，分别对应左右子树
         * 递归求解每个子树的所有非叶子节点的值得最小可能和
         *
         * dfs(i,j)表示arr [i,j]范围内所有非叶子节点的值的最小可能和  最终答案就是dfs(0,arr.length-1)
         */
        private Integer[][] dp;
        private int[][] mval;
        public int mctFromLeafValues(int[] arr) {
            int n = arr.length;
            dp = new Integer[n][n];
            mval = new int[n][n];
            // 数组mval[i][j] 记录数组arr中下标范围[i,j]内的所有叶节点的最大值
            for (int i = n-1; i >=0 ; i--) {
                // [i,i]只有一个数，即最大值
                mval[i][i]=arr[i];
                for (int j = i+1; j < n; j++) {
                    mval[i][j]=Math.max(mval[i][j-1],arr[j]);
                }
            }
            return dfs(0,n-1);

        }

        /**
         * dfs(i,j)表示arr [i,j]范围内所有非叶子节点的值的最小可能和
         * 对于[i,j]范围内，枚举每个位置的划分 arr[i,k] arr[k+1,j]
         *
         * 使用dp[i][j]记录dfs(i,j)，避免重复计算
         */
        private int dfs(int i,int j){
            // i=j,说明arr[i,j]中只有一个元素，不存在非叶子节点，所以和=0
            if (i==j){
                return 0;
            }
            if (dp[i][j]!=null){ // 避免重复计算，直接返回结果
                return dp[i][j];
            }

            int ans=Integer.MAX_VALUE;
            // 遍历[i,j]，考虑每个位置进行划分，记录最小和
            for (int k = i; k < j ; k++) {
                // 左子树非叶子节点和dfs(i,k)
                // 右子树非叶子节点和dfs(k+1,j)
                // 当前子树根节点左子树中的最大值*右子树中的最大值  mval[i][k]*mval[k+1][j]
                ans=Math.min(ans,dfs(i,k)+dfs(k+1,j)+mval[i][k]*mval[k+1][j]);
            }
            return dp[i][j]=ans;
        }


    }
}
