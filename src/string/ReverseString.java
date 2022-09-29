package string;

import java.util.Arrays;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/29 21:40
 * 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'H','a','n','n','a','h'};
        Solution solution = new Solution();
        solution.reverseString(s);
        System.out.println(Arrays.toString(s));

    }

    static class Solution {
        /**
         * 双指针  一个从第一个开始，一个从数组的最后一个开始，两两交换
         * @param s
         */
        public void reverseString(char[] s) {
            int p=0;
            int q=s.length-1;

            while (p<q){
                char tmp = s[p];
                s[p]=s[q];
                s[q]=tmp;
                p++;
                q--;
            }
        }
    }
}
