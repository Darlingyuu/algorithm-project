package array;

/**
 * 344. 反转字符串[easy]
 * https://leetcode.cn/problems/reverse-string/
 */
public class ReverseString {
    public static void main(String[] args) {
        String string = "hello";
        Solution solution = new Solution();
        solution.reverseString(string.toCharArray());
    }

    static class Solution {
        public void reverseString(char[] s) {
            int left=0,right=s.length-1;
            while (left<right){
                char tmp = s[left];
                s[left]=s[right];
                s[right]=tmp;
                left++;
                right--;
            }

//            System.out.println(Arrays.toString(s));
        }
    }
}
