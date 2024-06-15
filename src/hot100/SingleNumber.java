package hot100;

/**
 * 136. 只出现一次的数字[easy]
 * https://leetcode.cn/problems/single-number/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class SingleNumber {

    class Solution {
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res = res ^ nums[i];
            }
            return res;
        }
    }
}
