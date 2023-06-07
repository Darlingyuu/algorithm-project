package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I[easy]
 * https://leetcode.cn/problems/next-greater-element-i/
 */
public class NextGreaterElement {
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        Solution solution = new Solution();
        int[] ints = solution.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // 得到nums2每个位置的下一个更大元素的数组
            int[] greater=nextGreaterElement(nums2);
            // 将nums2[i]和比nums2[i]下一个更大元素greater[i]一一对应放入哈希表中
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums2.length; i++) {
                map.put(nums2[i],greater[i]);
            }

            // 遍历nums1, nums1[i]作为key来查找在nums2中对应的元素的下一个更大元素
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                res[i]=map.get(nums1[i]);
            }
            return res;
        }

        private int[] nextGreaterElement(int[] nums) {
            int[] res = new int[nums.length];
            Stack<Integer> stack = new Stack<>();
            // 倒序遍历
            for (int i = nums.length-1; i >=0; i--) {
                // 栈顶的元素不比当前元素大
                while (!stack.isEmpty() && stack.peek()<=nums[i]){
                    // 将栈顶元素弹出
                    stack.pop();
                }
                // stack中当前栈顶元素比nums[i]大
                res[i]=stack.isEmpty()?-1:stack.peek();
                // 将当前元素放入栈顶
                stack.add(nums[i]);
            }
            return res;
        }
    }
}
