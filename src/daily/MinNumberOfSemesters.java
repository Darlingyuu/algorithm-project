package daily;

import java.util.Arrays;

/**
 * 1494. 并行课程 II[medium]
 * https://leetcode.cn/problems/parallel-courses-ii/
 */
public class MinNumberOfSemesters {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] relations={{2,1},{3,1},{1,4}};
        System.out.println(solution.minNumberOfSemesters(4, relations, 2));
    }

    //存在k的限制，不能使用拓扑排序
    static class Solution {
        // pre[i] 表示 [1,i]中所有课程的先修课的并集
        private int[] pre;
        private int[] memo;
        private int k;
        private int u;
        public int minNumberOfSemesters(int n, int[][] relations, int k) {
            this.k=k;
            pre=new int[n];
            for (int[] edge : relations) {
                /**
                 * 此处使用二进制表示集合，用二进制中第i位上的1表示数字i存在于集合中，0表示不存在
                 * 比如{0,2,3}--->1101   对于单个数字而言 3--->  1<<3=1000
                 * 通过按位或运算可以求并集： 如{0,2} {0,2,3}  --->  101 | 1101 = 101 {0,2}
                 */
                int from = edge[0];
                int to = edge[1];
                // from -> to
                // pre[to] 表示 to的先修课的并集
                pre[to-1] |= 1<<(from-1);  // -1是为了将下标改为从1开始
            }

            // 全集,0到n-1位置上都是1
            u=(1<<n)-1;
            memo=new int[1<<n];
            // -1表示没有计算过
            Arrays.fill(memo,-1);
            // 全集进入递归
            return dfs(u);
        }

        // i表示课程集合
        // dfs(i)表示对于课程集合i，在一次最多学习k个的情况下，需要学习几次
        private int dfs(int i) {
            // 空集
            if (i==0){
                return 0;
            }
            // 之前算过
            if (memo[i]!=-1){
                return memo[i];
            }
            int i1=0;
            /**
             * 异或求补集  (异或，相同为0不同为1)
             * 全集为{0,1,2,3}，求{0,2}的补集
             * 1111 ^ 0101 = 1010  {1,3}
             */
            // i的补集
            int ci=u^i;

            /**
             * i 表示还未学习的课程
             * ci为i的补集，表示已经学完的课程
             * i1为i中可以直接学习的课程，i1的先修课程全部都在ci中
             */
            for (int j = 0; j < pre.length; j++) {
                // (i>>j & 1)>0  i的j位置上是1 课程j在集合中
                // (pre[j] | ci) == ci  pre1[j] 在 i 的补集中  (按位或运算求并集  pre1[j]是课程j的先修课程集合)
                if ((i>>j & 1)>0 && (pre[j] | ci) == ci){
                    // i1，记录下所有 课程在i中，但对应的先修课程在i的补集中的课程，即可以直接学习的课程
                    i1 |= 1<<j;
                }
            }

            // 如果个数小于 k，则可以全部学习，不再枚举子集
            // Integer.bitCount 统计一个数的二进制位有多少个1
            if (Integer.bitCount(i1)<=k){
                /**
                 * 按位异或，求差（子集）
                 * {0,1,2,3} ^  {1,3} -->  1111 ^ 1010 = 0101  {0,2}
                 */
                // i^i1 继续计算i与i1的差集
                return memo[i]=dfs(i^i1)+1;
            }

            int res=Integer.MAX_VALUE;
            // 枚举 i1 的子集 j
            for (int j = i1; j >0; j=(j-1)&i1) {
                if (Integer.bitCount(j)<=k){
                   res=Math.min(res,dfs(i^j)+1);
                }
            }
            return memo[i]=res;
        }

    }
}
