package string;

import java.util.HashMap;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/10/11 21:54
 */
public class FirstUniqChar {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstUniqChar("loveleetcode"));
    }

    static class Solution {
        public int firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            // 统计每个字符的数量
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (map.get(chars[i])!=null){
                    map.put(chars[i],map.get(chars[i])+1);
                }else {
                    map.put(chars[i],1);
                }
            }
            // 遍历每个字符，判断出现的次数是否为1次
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i))==1){
                    return i;
                }
            }
            return -1;

        }


        public int firstUniqCharII(String s) {
            //利用String api
            //一个从前查找，一个从后查找，如果下标相等，说明只出现了一次
            for (int i = 0; i < s.length(); i++) {
                if (s.indexOf(s.charAt(i))==s.lastIndexOf(s.charAt(i))){
                    return i;
                }
            }
            return -1;
        }
    }

}
