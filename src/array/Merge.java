package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 56. 合并区间[medium]
 * https://leetcode.cn/problems/merge-intervals/
 */
public class Merge {
    class Solution {
        public int[][] merge(int[][] intervals) {
            // 按照区间起点排序
            Arrays.sort(intervals,(a,b)->{
                return a[0]-b[0];
            });

            ArrayList<int[]> list = new ArrayList<>();
            int start=intervals[0][0];
            int end=intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int[] intv = intervals[i];
                // 有重叠，合并区间
                if (intv[0]<=end){
                    end=Math.max(end,intv[1]);
                }else {
                    // 无重叠，处理下一个待合并区间
                    list.add(new int[]{start,end});
                    start=intv[0];
                    end=intv[1];
                }
            }
            // 处理最后一个
            list.add(new int[]{start,end});

            int[][] res = new int[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                res[i]=list.get(i);
            }
            return res;
        }
    }
}
