package graph;

import java.util.ArrayList;

/**
 * 1584. 连接所有点的最小费用[medium]
 * https://leetcode.cn/problems/min-cost-to-connect-all-points/
 * Kruskal 算法（克鲁斯卡尔算法）
 */
public class MinCostConnectPoints {
    class Solution {
        public int minCostConnectPoints(int[][] points) {
            // 构建无向边
            ArrayList<int[]> list = new ArrayList<>();
            // 共n个点
            int n = points.length;
            // point[i]--point[j]
            for (int i = 0; i < n; i++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                for (int j = i+1; j < n; j++) {
                    int x2 = points[j][0];
                    int y2 = points[j][1];
                    // （x1,y1）--(x2,y2)
                    // 用下标i,j来标记point中哪两个点相连
                    list.add(new int[]{i,j,Math.abs(x1-x2)+Math.abs(y1-y2)});
                }
            }

            // 根据边的权重从小到大排序
            list.sort((a,b)->a[2]-b[2]);

            // 执行 Kruskal 算法
            int mst=0;
            // 并查集连通
            UF uf = new UF(n);
            for (int[] edge : list) {
                int p = edge[0];
                int q = edge[1];
                int weight = edge[2];
                // 如果p q连通，加这条边会形成环，不能加入
                if (uf.connected(p,q)){
                    continue;
                }

                // 不会形成环吗，则加入，属于最小生成树
                mst+=weight;
                uf.union(p,q);
            }
            return mst;
        }
    }

    class UF{
        // 连通分量
        private int count;
        // 每个点的父节点
        private int[] parent;

        public UF(int count) {
            this.count = count;
            parent=new int[count];
            for (int i = 0; i < count; i++) {
                parent[i]=i;
            }
        }


        public boolean connected(int p, int q) {
            int rootP=find(p);
            int rootQ=find(q);
            return rootP==rootQ;
        }

        private int find(int p) {
            // 找到根节点
            int root=p;
            while (root!=parent[root]){
                root=parent[root];
            }
            // 将该树上的节点都接在根节点下，压缩路径
            int old_parent=parent[p];
            while (p!=root){
                parent[p]=root;
                p=old_parent;
                old_parent=parent[p];
            }
            return root;
        }

        public void union(int p, int q) {
            int rootP=find(p);
            int rootQ=find(q);
            if (rootP==rootQ) return;
            parent[rootP]=rootQ;
            count--;
        }

        private int count(){
            return count;
        }
    }
}
