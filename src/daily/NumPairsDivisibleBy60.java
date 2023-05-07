package daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 1010. 总持续时间可被 60 整除的歌曲[medium]
 * 20220507
 */
public class NumPairsDivisibleBy60 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] time = {30,20,150,100,40};
//        int[] time = {60,60,60};
        int[] time = new int[60000];
        Arrays.fill(time,60); //1799970000
        int count = solution.numPairsDivisibleBy60(time);
        System.out.println(count);
    }

    static class Solution {
        public int numPairsDivisibleBy60(int[] time) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : time) {
                Integer count = map.get(num % 60);
                if (count==null){
                    map.put(num % 60,1);
                }else {
                    map.put(num % 60,count+1);
                }
            }
            Integer count = 0;
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                Integer num = iterator.next();
                if (num>30){
                    continue;
                }else {
                    if (num != 30 && map.get(60 - num) != null) {
                        count += map.get(60 - num) * map.get(num);
                    } else if ((num == 30 || num==0) && map.get(num) >= 2) {
                        // 注意越界
                        count = map.get(num)%2==0? count+map.get(num)/ 2 * (map.get(num) - 1) :  count+(map.get(num)-1)/ 2 * map.get(num);
                    }
                }
                iterator.remove();
            }
            return count;
        }

        //官方
        public int numPairsDivisibleBy60II(int[] time) {
            int[] cnt = new int[60];
            for (int t : time) {
                cnt[t % 60]++;
            }
            long res = 0;
            for (int i = 1; i < 30; i++) {
                res += cnt[i] * cnt[60 - i];
            }
            res += (long) cnt[0] * (cnt[0] - 1) / 2 + (long) cnt[30] * (cnt[30] - 1) / 2;
            return (int) res;
        }

    }

}
