package daily;

/**
 * 1401. 圆和矩形是否有重叠[medium]
 * https://leetcode.cn/problems/circle-and-rectangle-overlapping/
 */
public class CheckOverlap {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkOverlap(1, 0, 3, 7, 3, 10, 6));
    }
    static class Solution {
        public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
            // 求圆心到矩形区域的最短距离

            // 圆心在矩形内，距离为0
            double dist=0;
            // 圆心在矩形外，在左侧或者右侧
            if (xCenter<x1 || xCenter>x2){
                dist += Math.min(Math.pow(x1 - xCenter, 2), Math.pow(x2 - xCenter, 2));
            }
            // 圆心在矩形外，在上方或者下方
            if (yCenter < y1 || yCenter > y2) {
                dist += Math.min(Math.pow(y1 - yCenter, 2), Math.pow(y2 - yCenter, 2));
            }
            return dist <= radius * radius;
        }
//        public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
//            // 矩形 左下角(x1,y1),右上角(x2,y2)
//            // 检查是否相交或矩形在圆内
//            if ((check(radius, xCenter, yCenter, x1, y1, y2) ||  // 左边界
//                check(radius, xCenter, yCenter, x2, y1, y2) || // 右边界
//                checkII(radius, xCenter, yCenter, y2, x1, x2) || // 上边界
//                checkII(radius, xCenter, yCenter, y1, x1, x2))  // 下边界
//                || ( xCenter>=x1 &&xCenter<=x2 && yCenter>=y1&&yCenter<=y2)) { // 检查圆是否在矩形内
//                return true;
//            }
//            return false;
//        }
//
//        private boolean check(int radius, int xCenter, int yCenter, int x1, int y1, int y2) {
//            for (int i = y1; i <= y2; i++) {
//                int pow = (int)( Math.pow(Math.abs(x1 - xCenter), 2) + Math.pow(Math.abs(i - yCenter),2));
//                if (pow <= (int) Math.pow(radius, 2)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        private boolean checkII(int radius, int xCenter, int yCenter, int y1, int x1, int x2) {
//            for (int i = x1; i <= x2; i++) {
//                int pow = (int) (Math.pow(Math.abs(i - xCenter), 2) + Math.pow(Math.abs(y1 - yCenter),2));
//                if (pow <= (int) Math.pow(radius, 2)) {
//                    return true;
//                }
//            }
//            return false;
//        }
    }
}
