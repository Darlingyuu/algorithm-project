package stack;

/**
 * 1541. 平衡括号字符串的最少插入次数[medium]
 * https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/
 */
public class MinInsertions {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minInsertions("))())("));
    }

    static class Solution {

        public int minInsertions(String s) {
            // 记录插入次数
            int res = 0;
            // 记录右括号的需求量
            int need = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i)=='(') { // 遇到左括号
                    need+=2;
                    if (need%2==1){ //右括号少了,必须为偶数个
                        // 插入一个右括号
                        res++;
                        // 右括号需求减少
                        need--;
                    }
                }else {// 遇到右括号
                    need--;
                    if (need==-1){ // 右括号多了
                        // 插入一个左括号
                        res++;
                        // 右括号需求+2 =1
                        need+=2;
                    }
                }
            }
            return res+need;

        }
//        public int minInsertions(String s) {
//            // 记录插入次数
//            int res=0;
//            Stack<Character> left = new Stack<>();
//
//            for (int i = 0; i < s.length(); i++) {
//                if (s.charAt(i)=='('){ // 遇到左括号
//                    left.push(s.charAt(i));
//                }else {// 遇到右括号
//                    if (i!=s.length()-1 && s.charAt(i+1)!=')'){ // 不是连续两个右括号，需要插入一个右括号
//                        res++;
//                    }else if (i!=s.length()-1 && s.charAt(i+1)==')'){
//                        // 连续的 跳过第二个
//                        i++;
//                    }else { //i==s.length()-1
//                        // 插入一个右括号
//                        res++;
//                    }
//                    if (!left.isEmpty()) {
//                        left.pop();
//                    }else {
//                        // 需要插入一个左括号
//                        res++;
//                    }
//
//                }
//            }
//            // 中间插入括号的次数+最后还需要的右括号个数
//            return res+left.size()*2;
//        }
    }
}
