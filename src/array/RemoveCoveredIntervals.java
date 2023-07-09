package array;

import java.util.Arrays;

/**
 * 1288. 删除被覆盖区间[medium]
 * https://leetcode.cn/problems/remove-covered-intervals/
 */
public class RemoveCoveredIntervals {
    class Solution {
        public int removeCoveredIntervals(int[][] intervals) {
            // 按照起点升序排列，起点相同时降序排列
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });

            // 记录合并区间的起点和终点
            int left = intervals[0][0];
            int right = intervals[0][1];

            int res=0;
            for (int i = 1; i < intervals.length; i++) {
                int[] intv = intervals[i];
                // a <= b <= c <= d
                // 情况1：[a,d] [b,c] 找到了覆盖区间
                if (left<=intv[0] && right>=intv[1]){
                    res++;
                }
                // 情况2：[a,c] [b,d] 找到相交区间，合并
                if (right >= intv[0] && right <= intv[1]) {
                    right = intv[1];
                }
                // 情况3：[a,b] [c,d] 完全不相交，更新起点和终点
                if (right < intv[0]) {
                    left = intv[0];
                    right = intv[1];
                }
            }
            return intervals.length - res;
        }
    }
}
