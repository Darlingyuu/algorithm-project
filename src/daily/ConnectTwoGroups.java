package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1595. 连通两组点的最小成本[hard]
 * https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/
 */
public class ConnectTwoGroups {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> cost = new ArrayList<>();
        cost.add(Arrays.asList(93, 56, 92));
        cost.add(Arrays.asList(53, 44, 18));
        cost.add(Arrays.asList(86, 44, 69));
        cost.add(Arrays.asList(54, 60, 30));
        System.out.println(solution.connectTwoGroups(cost));
    }

    static class Solution {
        private List<List<Integer>> cost;
        private int[] minCost;
        private int[][] memo;

        public int connectTwoGroups(List<List<Integer>> cost) {
            this.cost=cost;
            int n = cost.size();
            int m = cost.get(0).size();
            // 记录第二组的每个点连接第一组的点的最小成本
            minCost=new int[m];
            Arrays.fill(minCost, Integer.MAX_VALUE);
            for (int i = 0; i < m; i++) {
                for (List<Integer> list : cost) {
                    minCost[i]=Math.min(minCost[i],list.get(i));
                }
            }

            // 二进制1<<m 用1 0 来记录第二组的各点是否连接
            // 1未连接 0 已连接
            memo=new int[n][1<<m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i],-1); // -1表示没有计算过
            }

            // 答案是第一组0到n-1的点连接第二组(1<<m)-1每一位代表的点  如m=3  (1<<m)-1=111 代表0 1 2 还未连接
            return dfs(n-1,(1<<m)-1);
        }

        // dfs(i,j) 表示第一组0到i的点连接二进制j中位置为1的点的最小代价
        private int dfs(int i, int j) {
            if (i<0){
                // 第一组的点都已连接，只需要管第二组的就行
                int res=0;
                // minCost记录了第二组的每个点连接第一组的点的最小成本
                for (int k = 0; k < minCost.length; k++) {
                    // j的第k位上为1，说明第二组的点k未连接
                    if (((j>>k) & 1)==1){
                        res += minCost[k]; // 去第一组找个成本最小的点连接
                    }
                }
                return res;
            }

            if (memo[i][j] != -1) return memo[i][j]; // 之前算过了

            int res=Integer.MAX_VALUE;
            for (int k = 0; k < minCost.length; k++) {
                // 第一组的点i与第二组的点k连接
                // 子问题：第一组点i-1与第二组除了点k之外的点连接
                // ~取反 1变0 0变1  j&~(1<<k)即将j的第k位上的1变成0
                // 当前问题=子问题+本次成本
                res=Math.min(res,dfs(i-1,j&~(1<<k))+cost.get(i).get(k));
            }
            return memo[i][j]=res;
        }

    }
}
