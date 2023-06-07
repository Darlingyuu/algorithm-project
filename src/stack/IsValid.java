package stack;

import java.util.Stack;

/**
 * 20. 有效的括号[easy]
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class IsValid {

    class Solution {
        public boolean isValid(String s) {
            // 存放左括号，出现栈顶的对应右括号则弹出
            Stack<Character> left = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c=='(' || c=='{'||c=='['){
                    left.push(c);
                }else { //遇到右括号
                    // 是否和栈顶的左括号匹配
                    if (!left.isEmpty() && left.peek()==leftOf(c)){
                        // 弹出栈顶 （该对匹配完毕）
                        left.pop();
                    }else {
                        // 和最近的左括号不匹配
                        return false;
                    }
                }
            }

            // 是否所有的左括号都被匹配了
            return left.empty();
        }

        // 返回c对应的左括号
        private Character leftOf(char c) {
            if (c == '}') return '{';
            if (c == ')') return '(';
            return '[';
        }
    }
}
