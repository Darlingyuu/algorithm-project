package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. 每日温度[hard]
 * https://leetcode.cn/problems/daily-temperatures/
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        Solution solution = new Solution();
        int[] ints = solution.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] res = new int[temperatures.length];
            // 存储下标
            Stack<Integer> stack = new Stack<>();
            for (int i = temperatures.length-1; i >=0; i--) {
                // 当栈顶温度不比当前高，就一直弹出栈顶温度
                while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]){
                    stack.pop();
                }

                res[i]=stack.isEmpty()?0:stack.peek()-i;
                stack.add(i);
            }
            return res;
        }
    }
}
