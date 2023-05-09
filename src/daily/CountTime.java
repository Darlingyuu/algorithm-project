package daily;

/**
 * 2437. 有效时间的数目[easy]
 * 20230509
 * https://leetcode.cn/problems/number-of-valid-clock-times/
 */
public class CountTime {
    public static void main(String[] args) {
//        String time ="?5:00";
//        String time ="?1:00";
//        String time ="01:00";
//        String time ="07:?3";
        String time ="0?:0?";
//        String time ="??:??";
        Solution solution = new Solution();
        System.out.println(solution.countTime(time));
    }

    static class Solution {
        public int countTime(String time) {
            if(time.length()!=5 || ':'!=time.charAt(2)){
                return -1;
            }

            int count=1;
            //判断小时位置
            if (time.charAt(0)=='?' && time.charAt(1)=='?'){
                count*=24;
            }else if (time.charAt(0)=='?' && time.charAt(1)!='?'){
                if (time.charAt(1)<='3'){
                    count*=3;
                }else {
                    count*=2;
                }
            }else if (time.charAt(0)!='?' && time.charAt(1)=='?'){
                if (time.charAt(0)=='2'){
                    count*=4;
                }else {
                    count*=10;
                }
            }

            //判断分钟位置
            if (time.charAt(3)=='?' && time.charAt(4)=='?'){
                count*=60;
            }else if (time.charAt(3)=='?' && time.charAt(4)!='?'){
                count *= 6;
            }else if (time.charAt(3)!='?' && time.charAt(4)=='?'){
                count*=10;
            }

            return count;
        }


        // 时间复杂度 O(24+60)，空间复杂度 O(1)。
        public int countTimeII(String time) {
            return f(time.substring(0, 2), 24) * f(time.substring(3), 60);
        }

        // '2'-'0'=2   '0'-'0'=0
        private int f(String s, int m) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                boolean a = s.charAt(0) == '?' || s.charAt(0) - '0' == i / 10;
                boolean b = s.charAt(1) == '?' || s.charAt(1) - '0' == i % 10;
                cnt += a && b ? 1 : 0;
            }
            return cnt;
        }
    }
}
