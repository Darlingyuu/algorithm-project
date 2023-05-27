package array;

import java.util.Arrays;

/**
 * 1109. 航班预订统计[medium]
 * https://leetcode.cn/problems/corporate-flight-bookings/
 */
public class CorpFlightBookings {
    public static void main(String[] args) {
        int[][] bookings={{3,3,5},{1,3,20},{1,2,15}};
        Solution solution = new Solution();
        int[] ints = solution.corpFlightBookings(bookings, 3);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            Diff diff = new Diff(bookings, n);
            return diff.getResult();
        }

        class Diff{
            int[] diff;
            int[] result;

            public Diff(int[][] bookings, int n){
                diff=new int[n+1];
                result=new int[n+1];

                for (int i = 0; i < bookings.length; i++) {
                    // 航班
                    int x = bookings[i][0];
                    int y = bookings[i][1];
                    // 预定数量
                    int num = bookings[i][2];
                    diff[x]+=num;
                    if (y+1<=n){
                        diff[y+1]-=num;
                    }
                }
            }

            public int[] getResult(){
                for (int i = 1; i < result.length; i++) {
                    result[i]=result[i-1]+diff[i];
                }
                return Arrays.copyOfRange(result,1,result.length);
            }
        }
    }
}
