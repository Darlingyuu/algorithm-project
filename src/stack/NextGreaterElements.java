package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503. 下一个更大元素 II[medium]
 * https://leetcode.cn/problems/next-greater-element-ii/
 */
public class NextGreaterElements {
    public static void main(String[] args) {
        int[] nums={1,2,1};
        Solution solution = new Solution();
        int[] ints = solution.nextGreaterElements(nums);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int len = nums.length;
            int[] res = new int[len];
            Stack<Integer> stack = new Stack<>();

            // 循环搜索，数组长度加倍模拟环形数组，根据下标取余确定元素
            for (int i = 2*(len-1); i >=0; i--) {
                while (!stack.isEmpty() && stack.peek()<=nums[i%len]){
                    stack.pop();
                }

                res[i%len]=stack.isEmpty()?-1:stack.peek();
                stack.add(nums[i%len]);
            }
            return res;
        }
    }
}
