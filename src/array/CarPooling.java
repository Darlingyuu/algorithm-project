package array;

/**
 * 1094. 拼车【medium]
 * https://leetcode.cn/problems/car-pooling/
 */
public class CarPooling {
    public static void main(String[] args) {
        int[][] trips = {{9, 0, 1}, {3, 3, 7}};
        Solution solution = new Solution();
        System.out.println(solution.carPooling(trips, 4));
    }

    static class Solution {
        public boolean carPooling(int[][] trips, int capacity) {
            Diff diff = new Diff(trips);
            return diff.result(capacity);
        }

        // 差分数组
        class Diff {
            int[] diffArr = new int[1001];

            public Diff(int[][] trips) {
                for (int i = 0; i < trips.length; i++) {
                    // 人数
                    int num = trips[i][0];
                    // 上车点
                    int up = trips[i][1];
                    // 下车点
                    int down = trips[i][2];
                    diffArr[up] += num;
                    // [up,down)在车上
                    if (down < diffArr.length + 1) { // down>=diffArr.length说明一直在车上
                        diffArr[down] -= num;
                    }
                }
            }

            public boolean result(int n) {
                // 原始数组每个位置的值
                int res = 0;
                for (int i = 0; i < diffArr.length; i++) {
                    res = res + diffArr[i];
                    if (res > n) {
                        return false;
                    }
                }
                return true;
            }
        }

//        class Sum {
//            int[] sumArr = new int[1001];
//
//            public Sum(int[][] trips) {
//                for (int i = 0; i < trips.length; i++) {
//                    // 人数
//                    int num = trips[i][0];
//                    // 上车点
//                    int up = trips[i][1];
//                    // 下车点
//                    int down = trips[i][2];
//                    for (int j = up; j < down; j++) {
//                        sumArr[j] += num;
//                    }
//                }
//            }
//
//            public boolean result(int n) {
//                for (int i : sumArr) {
//                    if (i > n) {
//                        return false;
//                    }
//                }
//                return true;
//            }
//        }
    }
}
