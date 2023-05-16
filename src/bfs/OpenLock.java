package bfs;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * 752. 打开转盘锁[medium]
 * https://leetcode.cn/problems/open-the-lock/
 */
public class OpenLock {
    public static void main(String[] args) {
        String[] deadends={"0201","0101","0102","1212","2002"};
//        String[] deadends={"8888"};
        Solution solution = new Solution();
        int count = solution.openLock(deadends, "0202");
        System.out.println(count);
    }

    static class Solution {
        public int openLock(String[] deadends, String target) {
            //用来记录已经访问过的字符串+deadends
            HashSet<String> isVisited = new HashSet<>();
            for (String deadend : deadends) {
                isVisited.add(deadend);
            }
            //记录转动次数
            int count=0;
            LinkedList<String> queue = new LinkedList<>();
            queue.add("0000");
            while (!queue.isEmpty()){
                int size = queue.size();
                // 遍历当前层
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    //结束条件
                    if (cur.equals(target)) return count;
                    //跳过的数字字符串，包括访问过的和死亡数字
                    if (isVisited.contains(cur)) continue;

                    isVisited.add(cur);

                    //扩散,字符串的四个位置
                    for (int j = 0; j < 4; j++) {
                        //向上旋转
                        String plus=plusOne(cur,j);
                        if (!isVisited.contains(plus)) {
                            queue.offer(plus);
                        }
                        //向下旋转
                        String down=downOne(cur,j);
                        if (!isVisited.contains(down)) {
                            queue.offer(down);
                        }
                    }
                }
                //次数增加
                count++;
            }
            return -1;
        }

        private String downOne(String cur,int j) {
            char[] array = cur.toCharArray();
            if (array[j]=='0'){
                array[j]='9';
            }else {
                array[j]-=1;
            }
            return new String(array);
        }

        private String plusOne(String cur,int j) {
            char[] array = cur.toCharArray();
            if (array[j]=='9'){
               array[j]='0';
            }else {
                array[j]+=1;
            }
            return  new String(array);
        }
    }



    class SolutionI {
        public int openLock(String[] deadends, String target) {
            //用来记录已经访问过的字符串+deadends
            HashSet<String> isVisited = new HashSet<>();
            for (String deadend : deadends) {
                isVisited.add(deadend);
            }
            //记录转动次数
            int count=0;
            LinkedList<String> queue = new LinkedList<>();
            queue.add("0000");
            while (!queue.isEmpty()){
                int size = queue.size();
                // 遍历当前层
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    //结束条件
                    if (cur.equals(target)) return count;
                    //跳过的数字字符串，包括访问过的和死亡数字
                    if (isVisited.contains(cur)) continue;

                    isVisited.add(cur);

                    //扩散,字符串的四个位置
                    for (int j = 0; j < 4; j++) {
                        char c = cur.charAt(j);
                        StringBuilder builder = new StringBuilder(cur);
                        //向上旋转
                        char plus=plusOne(c);
                        builder.setCharAt(j,plus);
                        queue.offer(builder.toString());
                        //向下旋转
                        char down=downOne(c);
                        builder.setCharAt(j,down);
                        queue.offer(builder.toString());
                    }
                }
                //次数增加
                count++;
            }
            return -1;
        }

        private char downOne(char c) {
            if (c=='0'){
                return '9';
            }
            return (char) (c-1);
        }

        private char plusOne(char c) {
            if (c=='9'){
                return '0';
            }
            return (char) (c+1);
        }
    }
}
