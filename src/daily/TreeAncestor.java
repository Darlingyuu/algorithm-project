package daily;

/**
 * 1483. 树节点的第 K 个祖先[hard]
 * https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/
 * 题解：https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/solutions/2305895/mo-ban-jiang-jie-shu-shang-bei-zeng-suan-v3rw/
 */
public class TreeAncestor {
    private int[][] pa;

    public TreeAncestor(int n, int[] parent) {
        /*
         * Integer.numberOfLeadingZeros(n)
         * 返回指定 int 值的二进制补码表示中最高位（"最左边"）一位之前的零位数
         * 如果指定的值在其二进制补码表示中没有一位，换句话说，如果它等于 0,则返回 32，。
         */
        // n 的二进制长度
        int m = 32 - Integer.numberOfLeadingZeros(n);
        // pa[i][j]记录节点i的第2^j个祖先节点
        pa = new int[n][m];
        // parent[i]就是节点i的第一个(2^0)祖先节点
        for (int i = 0; i < n; i++) {
            pa[i][0] = parent[i];
        }
        /*
         * 预处理每个节点x的第2^i个祖先节点,记为pa[x][i],不存在记为-1
         * pa[x][0]: 父节点
         * pa[x][1]=pa[ pa[x][0] ][0]: 爷爷节点
         * 一般地， pa[x][i+1]=pa[ pa[x][i] ][i]
         */

        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                // 节点x的第2^i个祖先节点
                int p = pa[x][i];
                // 节点x的第2^(i+1)个祖先节点 2^i*2  p的
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }

    }

    public int getKthAncestor(int node, int k) {
        // k 的二进制长度
        int m = 32 - Integer.numberOfLeadingZeros(k);
        for (int i = 0; i < m; i++) {
            if (((k >> i) & 1) > 0) {
                //k 的二进制从低到高第 i 位是 1
                node=pa[node][i];
                if (node<0) break;
            }
        }
        return node;
    }

    // 另一种写法，不断去掉 k 的最低位的 1
    public int getKthAncestor2(int node, int k) {
        for (; k > 0 && node != -1; k &= k - 1)
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        return node;
    }
}
