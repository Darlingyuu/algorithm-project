package hot100;

import java.util.*;

/**
 * 49. 字母异位词分组[medium]
 * https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs={"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = solution.groupAnagrams(strs);
        for (List<String> list : lists) {
            System.out.println(list.toString());
        }
    }
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // key=排序字母后的字符串 value=字母异位词
            HashMap<String,List<String>> map = new HashMap<>();

            for (String str : strs) {
                char[] array = str.toCharArray();
                // 对字符排序
                Arrays.sort(array);
                // 排序后转成字符串，看是否已经有相同的字符串存在在set中
                String string = String.valueOf(array);
//                if (map.containsKey(string)){
//                    // 存在相同的字母异位词，加入
//                    map.get(string).add(str);
//                }else {
//                    // 全新的，加入map
//                    ArrayList<String> list = new ArrayList<>();
//                    list.add(str);
//                    map.put(string,list);
//                }
                List<String> list = map.getOrDefault(string, new ArrayList<String>());
                list.add(str);
                map.put(string, list);

            }

            return new ArrayList<List<String>>(map.values());
        }
    }
}
