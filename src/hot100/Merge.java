package hot100;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 56. 合并区间[medium]
 * https://leetcode.cn/problems/merge-intervals/?envType=study-plan-v2&envId=top-100-liked
 */
public class Merge {
    class Solution {
        public int[][] merge(int[][] intervals) {
            // 按照区间起点排序
            Arrays.sort(intervals,(a,b)->{
                return a[0]-b[0];
            });

            ArrayList<int[]> list = new ArrayList<>();
            int left=intervals[0][0];
            int right=intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int l = intervals[i][0];
                int r = intervals[i][1];
                // 不重叠
                if (right<l){
                    // 将之前的加入list
                    list.add(new int[]{left,right});
                    left=l;
                    right=r;
                }else {
                    // 有重叠
                    right=Math.max(right,r);
                }
            }

            // 将最后一个区间加入
            list.add(new int[]{left,right});

            int[][] res = new int[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                res[i]=list.get(i);
            }
            return res;
        }
    }
}
