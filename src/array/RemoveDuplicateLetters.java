package array;

import java.util.Stack;

/**
 * 316. 去除重复字母[medium]
 * https://leetcode.cn/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        String s = "bcabc";
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicateLetters(s));
    }

    static class Solution {
        public String removeDuplicateLetters(String s) {
            char[] array = s.toCharArray();
            // 记录每个字符出现的次数
            int[] count = new int[256];
            for (char c : array) {
                count[c]++;
            }

            // 记录字符是否在栈中
            boolean[] inStack = new boolean[256];
            // 单调栈，维持顺序
            Stack<Character> stack = new Stack<>();
            for (char c : array) {
                // 遍历到的字符，次数减少
                count[c]--;
                //已经存在栈中，跳过
                if (inStack[c]) continue;

                // 不断与栈顶的元素比较，若栈顶元素字典值比c大，并且栈顶该元素后续还会出现则弹出
                while (!stack.isEmpty() &&c<stack.peek()){
                    if (count[stack.peek()]==0){
                        break;
                    }
                    inStack[stack.pop()]=false;
                }
                // 将该字符加入栈中
                stack.push(c);
                inStack[c]=true;
            }
            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.append(stack.pop());
            }
            return builder.reverse().toString();
        }
    }
}
