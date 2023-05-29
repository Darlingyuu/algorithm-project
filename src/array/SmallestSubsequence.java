package array;

import java.util.Stack;

/**
 * 1081. 不同字符的最小子序列[medium]
 * https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/
 */
public class SmallestSubsequence {
    public static void main(String[] args) {
        String s = "bcabc";
        Solution solution = new Solution();
        System.out.println(solution.smallestSubsequence(s));
    }

    static class Solution {
        public String smallestSubsequence(String s) {
            char[] array = s.toCharArray();
            // 维护一个计数器记录字符串中字符的数量
            // 因为输入为 ASCII 字符，大小 256 够用了
            int[] count = new int[256];
            for (char c : array) {
                count[c]++;
            }

            // 记录字符是否在栈中
            boolean[] inStack = new boolean[256];
            // 利用单调栈记录顺序
            Stack<Character> stack = new Stack<>();
            for (char c : array) {
                // 每遍历一个字符，计数-1
                count[c]--;
                // 如果该字符已经在栈中，跳过
                if (inStack[c]) continue;

                // 不再栈中
                // 栈不为空，并且栈顶的字典值比当前字符大
                // 只要栈顶的元素后面还有，则都弹出
                while (!stack.isEmpty() && stack.peek()>c){
                    // 若之后不存在栈顶元素了，则停止pop
                    if (count[stack.peek()]==0){
                        break;
                    }
                    // 若之后还有
                    inStack[stack.pop()]=false;
                }
                // 将该元素入栈
                stack.push(c);
                inStack[c]= true;
            }

            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.append(stack.pop());
            }
            return builder.reverse().toString();
        }
    }
}
