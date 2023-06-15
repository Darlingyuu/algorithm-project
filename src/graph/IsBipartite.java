package graph;

import java.util.LinkedList;

/**
 * 785. 判断二分图[medium]
 * https://leetcode.cn/problems/is-graph-bipartite/
 */
public class IsBipartite {
    class Solution {
        // 标记每个节点的颜色 true false表示不同颜色
        boolean[] color;
        // 标记节点是否访问过
        boolean[] visit;
        // 是否是二分图
        boolean flag=true;
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            // 标记每个节点的颜色 true false表示不同颜色
            color = new boolean[n];
            // 标记节点是否访问过
            visit = new boolean[n];

            // 因为图不一定是联通的，可能存在多个子图
            // 所以要把每个节点都作为起点进行一次遍历
            // 如果发现任何一个子图不是二分图，整幅图都不算二分图
            for (int i = 0; i < n; i++) {
                if (!visit[i]){
                    bfs(graph,i);
                }
            }
            return flag;
        }

        // 从 start 节点开始进行 BFS 遍历
        private void bfs(int[][] graph,int start) {
            LinkedList<Integer> queue = new LinkedList<>();
            // 将起点放入
            queue.offer(start);
            // 标记已访问
            visit[start]=true;
            // start起点默认染色false

            while (!queue.isEmpty() && flag){
                Integer cur = queue.poll();
                // 遍历cur的相邻节点
                for (int neigh : graph[cur]) {
                    if (!visit[neigh]){ // 未被访问
                        visit[neigh]=true;
                        // 染不同的颜色
                        color[neigh]=!color[cur];
                        // 放入队列
                        queue.offer(neigh);
                    }else { // 已访问
                        if (color[neigh]==color[cur]){
                            flag=false; // 有节点不满足二分图，直接结束
                            return;
                        }
                    }
                }
            }
        }
    }
}
