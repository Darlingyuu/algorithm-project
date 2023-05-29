package array;

import java.util.*;

/**
 * 710. 黑名单中的随机数[hard]
 * https://leetcode.cn/problems/random-pick-with-blacklist/
 */
public class Blacklist {
    public static void main(String[] args) {
        int[] blacklist={2,3,5};
        Solution solution = new Solution(7, blacklist);
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
    }

    static class Solution {
        private int sz;
        private Map<Integer,Integer> map;
        private Random random=new Random();
        public Solution(int n, int[] blacklist) {
            // 实际有效数字的个数
            sz=n-blacklist.length;
            map=new HashMap<>();
            // 将黑名单中的数字存入map
            for (int i : blacklist) {
                map.put(i,666);
            }

            int last=n-1;
            for (int num : blacklist) {
                if (num>=sz){// 如果num在[sz,n)，说明有效数字全部都在[0,sz)，可以不用管num
                    continue;
                }
                // 如果num在[0,sz),就将num指向[sz,n)里面的有效数
                // 从大到小遍历，找到第一个不在黑名单里面的数
                while (map.containsKey(last)){
                    last--;
                }
                // 将该数字指向这个找到的数
                map.put(num,last);
                // 继续下一个黑名单的数的指向
                last--;
            }
        }

        public int pick() {
            int rand = random.nextInt(sz);
            // 若这个索引命中map中的黑名单数字，会被指向正常数字
            if (map.containsKey(rand)){
                return map.get(rand);
            }
            return rand;
        }



        // n=1000000000 超出内存限制
//        private List<Integer> list;
//        private Random random=new Random();
//        private int size;
//        public Solution(int n, int[] blacklist) {
//            list=new ArrayList<>();
//            for (int i = 0; i < n; i++) {
//                list.add(i);
//            }
//            for (int i : blacklist) {
//                list.remove(Integer.valueOf(i));
//            }
//            size=list.size();
//        }
//
//        public int pick() {
//            int rand = random.nextInt(size);
//            return list.get(rand);
//        }
    }
}
