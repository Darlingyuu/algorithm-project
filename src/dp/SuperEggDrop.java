package dp;

import java.util.Arrays;

/**
 * 887. 鸡蛋掉落[hard]
 * https://leetcode.cn/problems/super-egg-drop/
 */
public class SuperEggDrop {

    class Solution {
        int[][] memo;
        public int superEggDrop(int k, int n) {
            // memo[i][j]记录i个鸡蛋j层楼，找到楼层f的最小操作次数
            memo = new int[k + 1][n + 1];
            for (int i = 0; i < k+1; i++) {
                Arrays.fill(memo[i],-1);
            }
            return dp(k,n);
        }

        // dp(k,n)返回k个鸡蛋n层楼，找到楼层f的最小操作次数(0 <= f <= n)
        private int dp(int k, int n) {
            // base case
            if (k==1){
                // 当我们只有一个鸡蛋的时候，只能进行线性扫描才能确定最坏情况下的恰好不碎的楼层
                return n;
            }
            //当楼层为0或者鸡蛋为0的时候，尝试的次数肯定为0
            if (k == 0 || n == 0) {
                return 0;
            };

            if (memo[k][n]!=-1) return memo[k][n];


            //状态转移方程：如果此时碎了，那么楼层在当前楼层的下面，楼层数为i-1 如果没有随楼层在当前楼层上面，楼层数为n-i
            // 因为是最坏情况下所以碎没碎取决于尝试的次数，选择尝试次数多的那个
            int res = Integer.MAX_VALUE;
            // 二分查找
            int lo=1,hi=n;
            while (lo<=hi){
                int mid=lo+(hi-lo)/2;
                // mid层楼时鸡蛋碎了，向下找
                int broke  = dp(k-1, mid-1);
                // mid层楼时鸡蛋没碎，向上找
                int not_broke  = dp(k, n-mid);
                // 悲观，找次数大的
                if (broke > not_broke) {
                    //碎的情况下尝试的次数多于不碎
                    //下次我们应该去楼下尝试
                    hi = mid-1;
                    //因为最后结果是尝试次数最少，所以取最小值
                    res = Math.min(res, broke + 1);
                } else {
                    //不碎的情况下尝试的次数多于碎
                    //下次我们应该去楼上尝试
                    lo = mid + 1;
                    res = Math.min(res, not_broke + 1);
                }
            }

//            // 记录最小操作次数
//            int res=Integer.MAX_VALUE;
//            for (int i = 1; i <= n; i++) {
//                // 悲观选择次数大的  第i层
//                // 碎了 鸡蛋少一个，往下层找，子问题dp(k-1,i-1)
//                // 没碎 往上找子问题dp(k,n-i)
//                // 再加上本次操作
//                res=Math.min(res,Math.max(dp(k-1,i-1),dp(k,n-i))+1);
//            }
            return memo[k][n]=res;
        }


        public int superEggDropII(int k, int n) {
            //dp数组的定义:dp[k][m]表示有k个鸡蛋，最少尝试m次可以确定的最高的楼层是dp[k][m]
            //这里为什么大小设置为[n+1]呢。因为确定楼层N层需要的最少的次数最多也就是n次
            int[][] dp = new int[k + 1][n + 1];
            //base case
            //1.当鸡蛋为0的时候，确定的高度为0
            for (int m = 0; m < n + 1; m++) {
                dp[0][m] = 0;
            }
            //2.当尝试次数m为0的时候可以确定的楼层也为0
            for (int i = 0; i < k - 1; i++) {
                dp[i][0] = 0;
            }

            //当我们可以确定的楼层高度达到了n层，那么就结束了
            int m = 0;
            while (dp[k][m] < n) {
                m++;//尝试的次数+1
                for (int s = 1; s <= k; s++) {
                    //当前可以确定的最高高度=楼下的高度+楼上的高度+1
                    //dp[s][m-1]代表没碎，所以确定楼下的高度
                    //dp[s-1][m-1]代表碎了，确定楼上的高度
                    //加1是当前的楼层
                    dp[s][m] = dp[s][m - 1] + dp[s - 1][m - 1] + 1;
                }
            }
            return m;
        }
    }
}
