package array;

import java.util.ArrayList;

/**
 * 986. 区间列表的交集[medium]
 * https://leetcode.cn/problems/interval-list-intersections/
 */
public class IntervalIntersection {
    class Solution {
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            // 双指针
            int i=0,j=0;
            ArrayList<int[]> list = new ArrayList<>();
            while (i<firstList.length && j<secondList.length){
                int a1 = firstList[i][0];
                int a2 = firstList[i][1];
                int b1 = secondList[j][0];
                int b2 = secondList[j][1];
                // 两个区间不存在交集 a2<b1 || b2<a1
                // 两个区间存在交集 上述取反
                if (a2>=b1 && b2>=a1){
                    list.add(new int[]{Math.max(a1,b1),Math.min(a2,b2)});
                }
                // 指针前进
                if (b2<a2){
                    j++;
                }else {
                    i++;
                }
            }
            int[][] res = new int[list.size()][];
            for (int k = 0; k < list.size(); k++) {
                res[k]=list.get(k);
            }
            return res;
        }
    }
}
