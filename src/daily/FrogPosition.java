package daily;

import java.util.*;

/**
 * 1377. T 秒后青蛙的位置[hard]
 * https://leetcode.cn/problems/frog-position-after-t-seconds/
 */
public class FrogPosition {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
        System.out.println(solution.frogPosition(7, edges, 2, 4));
    }
    static class Solution {

        public double frogPosition(int n, int[][] edges, int t, int target) {
            // 邻接表
            ArrayList<Integer>[] lists = new  ArrayList[n+1];
            // 初始化
            for (int i = 1; i <= n; i++) {
                lists[i]=new ArrayList<>();
            }
            for (int[] edge : edges) {
                int cur = edge[0];
                int nxt = edge[1];
                lists[cur].add(nxt);
                lists[nxt].add(cur); // 将子节点连父节点的路径加入  无向图
            }
             boolean[] isVisited=new boolean[n+1];
            return dfs(lists,isVisited,1,t,target);
        }

        /**
         *
         * @param lists 邻接矩阵
         * @param isVisited 记录访问情况
         * @param i 当前起点的值
         * @param t 时间 秒
         * @param target 目标值
         * @return
         */
        private double dfs(ArrayList<Integer>[] lists, boolean[] isVisited, int i, int t, int target) {
            // 记录i点可走的路径个数
            // 如果是起点1，数量即为对应list的大小
            // 如果不是起点1，因为不能回跳，要减去i点到它的父节点的路径，所以为list-1
            int nxt = i == 1 ? lists[i].size() : lists[i].size() - 1;

            // 时间耗尽或不存在向下的路径
            if (t==0 || nxt==0){
                // 当前节点是否是目标节点，返回概率
                return i==target ? 1.0:0.0;
            }

            // 将当前节点置为已访问
            isVisited[i]=true;
            double probability=0.0;
            // 对该节点的向下路径进行变例
            for (Integer j : lists[i]) {
                if (!isVisited[j]){
                    // 对未曾访问过的节点当作最新的起点，继续递归其向下路径
                    // 时间耗费1秒
                    probability+=dfs(lists,isVisited,j,t-1,target);
                }
            }
            return probability/nxt;
        }


    }

}
