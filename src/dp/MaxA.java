package dp;

/**
 * 651. 4键键盘[medium]
 * 假设你有一个特殊的键盘，上面只有四个键，它们分别是：
 * 1、A 键：在屏幕上打印一个 A。
 * 2、Ctrl-A 键：选中整个屏幕。
 * 3、Ctrl-C 键：复制选中的区域到缓冲区。
 * 4、Ctrl-V 键：将缓冲区的内容输入到光标所在的屏幕上。
 * 这就和我们平时使用的全选复制粘贴功能完全相同嘛，只不过题目把 Ctrl 的组合键视为了一个键。
 * 现在要求你只能进行 N 次操作，请你计算屏幕上最多能显示多少个 A？
 */
public class MaxA {
    class Solution {
        public int maxA(int N) {
            /**
             * 选择: A、C-A、C-C、C-V（Ctrl 简写为 C）
             * 状态: 剩余的敲击次数 n
             *
             * 优按键序列一定只有两种情况：
             *      要么一直按 A：A,A,...A（当 N 比较小时）。
             *      要么是这么一个形式：A,A,...C-A,C-C,C-V,C-V,...C-V（当 N 比较大时）
             *
             * 最后一次按键要么是 A 要么是 C-V
             */
            // dp[i]表示i次操作后最多显示多少个A
            int[] dp = new int[N + 1];
            // 穷举状态
            for (int i = 1; i <= N; i++) {
                // 穷举选择 A  C-V
                // A  dp[i]=dp[i-1]+1
                dp[i]=dp[i-1]+1;
                // C-V
                // 最优的操作序列一定是 C-A C-C 接着若干 C-V
                // 用j作为若干 C-V 的起点，那么 j 之前的 2 个操作就应该是 C-A C-C 了
                for (int j = 2; j < i; j++) { // j最小为2
                    // 第i次操作是最后一次C-V
                    // 第一次C-V是第j次操作,所以连续粘贴了i-j+1次
                    // 第j-1次操作是C-C,第j-2次操作是C-A,所以每次粘贴增加的A的个数为dp[j-2]
                    // dp[i]取A C-A较大者
                    dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
                }
            }
            return dp[N];
        }
    }
}
