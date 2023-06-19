package graph;

/**
 * 990. 等式方程的可满足性[medium]
 * https://leetcode.cn/problems/satisfiability-of-equality-equations/
 */
public class EquationsPossible {
    class Solution {
        public boolean equationsPossible(String[] equations) {
            // 26 个英文字母
            UF uf = new UF(26);
            // 先让==的字母形成连通分量
            for (String equation : equations) {
                if (equation.charAt(1) == '=') {
                    char x = equation.charAt(0);
                    char y = equation.charAt(3);
                    uf.union(x - 'a', y - 'a');
                }
            }

            // 检查!= 是否打破相等关系的字母的连通性
            for (String equation : equations) {
                if (equation.charAt(1) == '!') {
                    char x = equation.charAt(0);
                    char y = equation.charAt(3);
                    // 如果相等关系成立，就是逻辑冲突
                    if (uf.connected(x - 'a', y - 'a')) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    // 并查集算(Union-Find)法
    class UF{
        // 连通分量
        private int count;
        // 储存每个节点的父节点
        private int[] parent;

        // n为图中节点个数
        public UF(int n){
            // 一开始互不连通
            this.count=n;
            // 初始化，父节点指针初始指向自己
            parent=new int[n];
            for (int i = 0; i < n; i++) {
                parent[i]=i;
            }
        }

        /**
         * 如果某两个节点被连通，则让其中的（任意）一个节点的根节点接到另一个节点的根节点上：
         */
        // 将节点p 节点q连通
        public void union(int p,int q){
            int rootP=find(p);
            int rootQ=find(q);
            if (rootP==rootQ){ // 根节点相同，说明p q在同一颗树上已经是连通状态，无需操作
                return;
            }
            // 将两棵树合并为一棵
            parent[rootQ] = rootP;

            // 两个分量合二为一
            count--;
        }

        // 返回某个节点 x 的根节点  只有根节点parent[x]==x(最开始初始化的时候将父节点指向自己)
        // 路径压缩，把 x 到根节点之间的所有节点直接接到根节点下面
        private int find(int x) {
            // 先找到根节点
            int root=x;
            while (parent[root]!=root){
                root=parent[root];
            }

            // 把 x 到根节点之间的所有节点直接接到根节点下面
            int old_parent=parent[x];
            while (x!=root){
                parent[x]=root;
                x=old_parent;
                old_parent=parent[old_parent];
            }

            return root;
        }

        // 判断节点p 节点q是否连通
        public boolean connected(int p, int q) {
            int rootP=find(p);
            int rootQ=find(q);
            // 根节点相同，说明p q在同一颗树上已经是连通状态
            return rootP==rootQ;

        }


        // 返回当前的连通分量个数
        public int count() {
            return count;
        }
    }
}
