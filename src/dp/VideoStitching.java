package dp;

import java.util.Arrays;

/**
 * 1024. 视频拼接[medium]
 * https://leetcode.cn/problems/video-stitching/
 */
public class VideoStitching {

    class Solution {
        // 动态规划
        public int videoStitching(int[][] clips, int time) {
            // dp[i]表示将[0,i]覆盖所需的最少子区间数量
            int[] dp = new int[1 + time];
            Arrays.fill(dp,Integer.MAX_VALUE/2);
            dp[0]=0;
            for (int i = 0; i <= time; i++) {
                for (int[] clip : clips) {
                    // [clip[0],clip[1]]
                    // i在(clip[0],clip[1]]范围内
                    if (clip[0]<i && i<=clip[1]){
                        // dp[i]取值，要么前面算出来的dp[i],要么[0,clip[0]]的最小数量+本次
                        dp[i]=Math.min(dp[i],dp[clip[0]]+1);
                    }
                }
            }
            return dp[time]==Integer.MAX_VALUE/2?-1:dp[time];
        }

        // 贪心
        public int videoStitchingII(int[][] clips, int T) {
            if (clips == null) {
                return 0;
            }

            int[] maxEnd = new int[T];  // 用于保存 以当前数字(下标)为起点 的区间的 最大的结束位置

        /*
            遍历clips，初始化maxEnd数组(每个元素开头的区间的最大结束位置)
         */
            for (int[] clip : clips) {
                if (clip[0] < T) {
                    maxEnd[clip[0]] = Math.max(maxEnd[clip[0]], clip[1]);
                }
            }

        /*
            根据maxEnd数组，计算最终结果
                因为maxEnd[i]数组为最大结束位置，因此：
                    1、当前元素 == 本区间最大元素，
                        即：区间断开，无法连续到后续位置，返回-1
                    2、当前元素 == 上一个区间的最大结束元素，
                        即：到达了上一个满足条件的区间的结束位置
                        这时的last为当前最大的结束位置，我们将其放入满足条件的区间集合之中
                        (因为本题只需要我们记录 满足条件的区间个数，因为只需要 更新count和pre 即可)
         */
            int pre = 0;    // 记录 结果中上一次的最大结束位置(本轮的最小开始位置)
            int last = 0;   // 记录当前遍历到的 区间最大结束位置
            int count = 0; // 记录结果
            for (int i = 0; i < T; i++) {
                last = Math.max(maxEnd[i], last);
                if (i == last) {    // 当前元素 == 本区间最大元素(无法到达后续位置)
                    return -1;
                }

                if (i == pre) { // 当前元素 == 上一个区间的最大元素
                    count++;
                    pre = last;
                }
            }
            return count;
        }
    }
}
