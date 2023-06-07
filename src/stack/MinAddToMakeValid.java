package stack;

/**
 * 921. 使括号有效的最少添加[medium]
 * https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
 */
public class MinAddToMakeValid {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minAddToMakeValid("()))(("));
    }
    static class Solution {
        public int minAddToMakeValid(String s) {
            // 记录插入次数
            int res = 0;
            // 记录右括号的需求量
            int need = 0;
            for (char c : s.toCharArray()) {
                // 遇到左括号
                if (c == '(') {
                    // 对右括号的需求增加
                    need++;
                } else {
                    // 遇到右括号,对右括号的需求减少
                    need--;

                    if (need == -1) { // 说明右括号多了一个
                        need = 0;
                        // 需要插入一个左括号
                        res++;
                    }
                }
            }
            // 左括号插入次数+需要的右括号数量
            return res + need;
        }

//        public int minAddToMakeValid(String s) {
//            // 记录插入次数
//            int res=0;
//            Stack<Character> left = new Stack<>();
//            for (char c : s.toCharArray()) {
//                // 遇到左括号
//                if (c=='('){
//                    left.push(c);
//                }else { // 遇到右括号
//                    if (!left.isEmpty()){
//                        left.pop();
//                    }else {
//                        // 左括号不够，插入一个左括号
//                        res++;
//                    }
//                }
//            }
//            // 插入的左括号+最后需要额外插入的右括号数量
//            return res+left.size();
//        }
    }
}
