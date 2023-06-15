package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 886. 可能的二分法[medium]
 * https://leetcode.cn/problems/possible-bipartition/
 */
public class PossibleBipartition {
    class Solution {
        // 标记是否是二分图
        private boolean flag=true;
        // 标记每个节点的颜色
        private boolean[] color;
        // 标记每个节点是否访问过
        private boolean[] visit;
        public boolean possibleBipartition(int n, int[][] dislikes) {
            // 图节点编号从1开始
            color=new boolean[n+1];
            visit=new boolean[n+1];
            // 构建图(邻接表)
            List<Integer>[] graph=buildGraph(n,dislikes);

            // 每个节点不一定都是连通的
            for (int i = 1; i <= n; i++) {
                // 只对未遍历到的节点遍历
                if (!visit[i]){
                    bfs(graph,i);
                }
            }
            return flag;
        }

        private void bfs(List<Integer>[] graph, int start) {
            LinkedList<Integer> queue = new LinkedList<>();
            queue.offer(start);
            visit[start]=true;
            while (!queue.isEmpty()&& flag){
                Integer cur = queue.poll();
                // 遍历邻居
                for (Integer neigh : graph[cur]) {
                    if (!visit[neigh]){ //没有遍历过
                        visit[neigh]=true;
                        color[neigh]=!color[cur];
                        queue.offer(neigh);
                    }else { // 遍历过
                        if (color[neigh]==color[cur]){
                            // 颜色相同，不是二分图
                            flag=false;
                            return;
                        }

                    }
                }
            }
        }

        // 构建图(邻接表)
        private List<Integer>[] buildGraph(int n, int[][] dislikes) {
            // 图节点编号从1开始
            List<Integer>[] graph=new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i]=new ArrayList<>();
            }

            for (int[] edge : dislikes) {
                // 无向图，相当于双向图
                int v = edge[0];
                int w = edge[1];
                // v->w
                graph[v].add(w);
                // w->v
                graph[w].add(v);
            }
            return graph;
        }
    }
}
