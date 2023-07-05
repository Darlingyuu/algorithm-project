package dp;

import java.util.Arrays;

/**
 * 452. 用最少数量的箭引爆气球[medium]
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 */
public class FindMinArrowShots {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] points={{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(solution.findMinArrowShots(points));
    }
    static class Solution {
        public int findMinArrowShots(int[][] points) {
            // Xend从小到大排序
            Arrays.sort(points,(a,b)->{
//                if (a[1]-b[1]>0) return 1;
//                if (a[1]-b[1]<0) return -1;
                // 不可用上面的减法运算，会溢出
                if (a[1]>b[1]) return 1;
                if (a[1]<b[1]) return -1;
                return 0;
            });

            // 记录箭的个数
            int count=1;
            // 最开始Xend
            int Xend=points[0][1];
            for (int[] point : points) {
                int Xstart = point[0];
                if (Xstart<=Xend){ // 可以共用一个箭
                    // 交集结束点作为Xend
                    Xend=Math.min(Xend,point[1]);
                }else { // 无法共用一个
                    count++;
                    // 更新新的Xend
                    Xend=point[1];
                }
            }
            return count;
        }
    }
}
