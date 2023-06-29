package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 787. K 站中转内最便宜的航班[medium]
 * https://leetcode.cn/problems/cheapest-flights-within-k-stops/
 */
public class FindCheapestPrice {
    class Solution {
        int src;
        int dst;
        // 哈希表记录每个点的入度
        HashMap<Integer, List<int[]>> indegree;
        int[][] memo;
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            this.src = src;
            this.dst = dst;
            // memo[i][j]表示从起点0到起点i，j步内最小路径权重(最多k+1步)
            memo = new int[n][k + 2];
            // 初始化备忘录，全部填一个特殊值
            for (int[] row : memo) {
                Arrays.fill(row, -888);
            }

            // 记录每个点的入度
            // to 的入度是  [from, price]
            indegree = new HashMap<>();
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                // from->to  from是to的入度
                // 记录谁指向该节点，以及之间的权重
                indegree.putIfAbsent(to, new LinkedList<>());
                indegree.get(to).add(new int[]{from, price});
            }

            // 中转站个数为k，则经过的边就是k+1
            return dp(dst, k + 1);
        }

        // dp(s,k)返回从起点出发，k步(一步就是一条边)之内到节点s的最小路径权重
        private int dp(int s, int k) {
            // base case
            if (s == src) { // 就在起点，不用走
                return 0;
            }
            // 步数用尽，无解
            if (k == 0) {
                return -1;
            }

            // 查备忘录，防止冗余计算
            if (memo[s][k] != -888) {
                return memo[s][k];
            }

            // 初始化为最大值，方便等会取最小值
            int res = Integer.MAX_VALUE;
            if (indegree.containsKey(s)) {
                // 当 s 有入度节点时，分解为子问题
                for (int[] v : indegree.get(s)) {
                    int from = v[0];
                    int price = v[1];
                    // from -> s 为一步，所以起点到from节点，要在k-1步内
                    // 子问题为从 src 到达相邻的from节点所需的最短路径权重
                    int subProblem = dp(from, k - 1);
                    // 跳过无解情况
                    if (subProblem != -1) {
                        // res记录通过各入度节点，src到s节点的最短路径权重
                        res = Math.min(res, subProblem+price);
                    }
                }
            }
            // 如果还是初始值，说明此节点不可达
            return memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        }
    }
}
