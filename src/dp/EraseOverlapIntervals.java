package dp;

import java.util.Arrays;

/**
 * 435. 无重叠区间[medium]
 * https://leetcode.cn/problems/non-overlapping-intervals/
 */
public class EraseOverlapIntervals {

    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            //根据每个区间的end进行一个升序排列
            // 保证后面的序列的start是大于等于这个区间的结尾才能说明这两个区间是没有交集的
            // Arrays.sort(points,(a,b)->(a[1]-b[1]));// 可能出现溢出情况
            Arrays.sort(intervals,(a,b)->{
                if (a[1]>b[1]) return 1;
                if (a[1]<b[1]) return -1;
                return 0;
            });

            //count来记录没有交集的区间，最后的结果就是intervals元素的个数减去这个没有交集的区间个数
            int count=1;
            //开始的end肯定是end最小的那个
            int start_end = intervals[0][1];
            for (int[] interval : intervals) {
                int start = interval[0];
                if (start>=start_end){
                    //如果后面的区间的start是比此时的start_end大或者等于的时候，说明两个区间也是没有交集的
                    count++;
                    start_end=interval[1];
                }
                // 否则就是有交集
            }
            return intervals.length-count;
        }
    }
}
