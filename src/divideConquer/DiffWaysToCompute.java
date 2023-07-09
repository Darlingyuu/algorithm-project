package divideConquer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级[medium]
 * https://leetcode.cn/problems/different-ways-to-add-parentheses/
 */
public class DiffWaysToCompute {
    class Solution {

        // 计算算式 expression 所有可能的运算结果
        // expression 由数字和算符 '+'、'-' 和 '*' 组成。

        // 备忘录
        HashMap<String, List<Integer>> memo = new HashMap<>();
        public List<Integer> diffWaysToCompute(String expression) {
            // 避免重复计算
            if (memo.containsKey(expression)) {
                return memo.get(expression);
            }

            LinkedList<Integer> res = new LinkedList<>();
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                // 扫描算式中的运算符
                if (c == '-' || c == '*' || c == '+') {
                    /****** 分 ******/
                    // 以运算符为中心，分割成两个字符串，分别递归计算
                    List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> right = diffWaysToCompute(expression.substring(i+1));
                    /****** 治 ******/
                    // 通过子问题的结果，合成原问题的结果
                    for (Integer a : left) {
                        for (Integer b : right) {
                            if (c == '+')
                                res.add(a + b);
                            else if (c == '-')
                                res.add(a - b);
                            else if (c == '*')
                                res.add(a * b);
                        }
                    }
                }
            }

            // base case
            // 如果 res 为空，说明算式是一个数字，没有运算符
            if (res.isEmpty()) {
                res.add(Integer.parseInt(expression));
            }

            // 将结果添加进备忘录
            memo.put(expression, res);
            return res;
        }
    }
}
