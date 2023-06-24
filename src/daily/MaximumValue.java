package daily;

/**
 * 2496. 数组中字符串的最大值[easy]
 * https://leetcode.cn/problems/maximum-value-of-a-string-in-an-array/
 */
public class MaximumValue {

    public static void main(String[] args) {
    }
    class Solution {
        public int maximumValue(String[] strs) {
            int ans=0;
            for (int i = 0; i < strs.length; i++) {
                String str = strs[i];
                boolean onlyNum=true;
                for (char c : str.toCharArray()) {
                    if (Character.isLetter(c)){
                        onlyNum=false;
                    }
                }
                // Integer.parseInt(str)  "001"-->1  "4"-->4
                ans=Math.max(ans, onlyNum?Integer.parseInt(str):str.length());
            }
            return ans;
        }
    }
}
