package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2699. 修改图中的边权[hard]
 * https://leetcode.cn/problems/modify-graph-edge-weights/
 */
public class ModifiedGraphEdges {
    public static void main(String[] args) {

    }

    class Solution {
        public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
            List<int[]> g[] = new ArrayList[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int i = 0; i < edges.length; i++) {
                int x = edges[i][0], y = edges[i][1];
                g[x].add(new int[]{y, i});
                g[y].add(new int[]{x, i}); // 建图，额外记录边的编号
            }

            // 记录最短路径  source到点i的最短路径长度为dis[i][1]
            int[][] dis = new int[n][2];
            for (int i = 0; i < n; i++) {
                if (i != source) {
                    dis[i][0] = dis[i][1] = Integer.MAX_VALUE;
                }
            }
            //先把边权-1都修改成 1，然后跑第一遍 Dijkstra
            // 设从 source到点i的最短路径长度为dis[i][0]
            dijkstra(g, edges, destination, dis, 0, 0);
            int delta = target - dis[destination][0];
            if (delta < 0) // -1 全改为 1 时，最短路比 target 还大
                return new int[][]{};

            dijkstra(g, edges, destination, dis, delta, 1);
            if (dis[destination][1] < target) {
                // 最短路无法再变大，无法达到 target
                return new int[][]{};
            }

            for (int[] e : edges)
                if (e[2] == -1) // 剩余没修改的边全部改成 1
                    e[2] = 1;
            return edges;
        }

        // 朴素 Dijkstra 算法
        // 这里 k 表示第一次/第二次
        private void dijkstra(List<int[]>[] g, int[][] edges, int destination, int[][] dis, int delta, int k) {
            int n = g.length;
            boolean[] vis = new boolean[n];
            for (;;){
                // 找到当前最短路，去更新它的邻居的最短路
                // 根据数学归纳法，dis[x][k] 一定是最短路长度
                int x=-1;
                for (int i = 0; i < n; i++) {
                    if (!vis[i] && (x<0 || dis[i][k]<dis[x][k])){
                        x=i;
                    }
                }
                // 到达终点，起点 source 到终点 destination 的最短路已确定
                if (x==destination) return;

                // 标记，在后续的循环中无需反复更新 x 到其余点的最短路长度
                vis[x]=true;
                // 遍历x点的连通点
                for (int[] e : g[x]) {
                    // 连通点
                    int y = e[0];
                    // x-y的边权在edges中下标位置
                    int eid = e[1];
                    // 原始的边权
                    int wt = edges[eid][2];
                    if (wt==-1){
                        // -1 改成 1
                        wt=1;
                    }
                    if (k==1 && edges[eid][2]==-1){
                        // 第二次 Dijkstra，改成 w
                        int w = delta + dis[y][0] - dis[x][1];
                        if (w > wt)
                            edges[eid][2] = wt = w; // 直接在 edges 上修改
                    }
                    // 更新最短路
                    dis[y][k] = Math.min(dis[y][k], dis[x][k] + wt);
                }
            }
        }
    }
}
