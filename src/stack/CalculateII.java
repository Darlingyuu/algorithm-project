package stack;

import java.util.Stack;

/**
 * 227. 基本计算器 II[medium]
 */
public class CalculateII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("3+5 / 2"));
    }

    static class Solution {
        public int calculate(String s) {
            // 存放数字
            Stack<Integer> stack = new Stack<>();
            // 记录遇到的数字
            int num = 0;
            // 记录运算符,初始化为加号
            char opa = '+';
            char[] array = s.toCharArray();
            int i = 0;

            while (i < array.length) {
                if (Character.isDigit(array[i])){
                    // 数字
                    num = num * 10 + (array[i] - '0');
                }

                if ((!Character.isDigit(array[i]) && array[i] != ' ') || i == array.length - 1) { // 遇到运算符 或 遍历到最后
                    if (opa == '+') {
                        // 加号，把num记录的数字加入到stack中
                        stack.push(num);
                    } else if (opa == '-') {
                        // 减号，把num记录的数字的负数加入到stack中
                        stack.push(-num);
                    } else if (opa == '*') {
                        // 乘号，取出前一个数字与当前数字运算,将结果放入stack
                        stack.push(stack.pop() * num);
                    } else if (opa == '/') {
                        // 除号，取出前一个数字与当前数字运算,将结果放入stack
                        stack.push(stack.pop() / num);
                    }
                    // 更新num opa
                    num = 0;
                    opa = array[i];
                }

                i++;
            }

            // 取出stack中的所有数字，相加得到结果
            int res = 0;
            while (!stack.isEmpty()) {
                res += stack.pop();
            }
            return res;
        }
    }
}
