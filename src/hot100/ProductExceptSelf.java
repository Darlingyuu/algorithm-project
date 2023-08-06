package hot100;

/**
 * 238. 除自身以外数组的乘积[medium]
 * https://leetcode.cn/problems/product-of-array-except-self/?envType=study-plan-v2&envId=top-100-liked
 */
public class ProductExceptSelf {
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            // dp1[i+1]  [0,i]的乘积
            int[] dp1 = new int[nums.length + 1];
            dp1[0] = 1;
            for (int i = 0; i < nums.length; i++) {
                dp1[i + 1] = dp1[i] * nums[i];
            }

            // dp1[i]  [0,i]的乘积
            int[] dp2 = new int[nums.length + 1];
            dp2[nums.length] = 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                dp2[i] = dp2[i + 1] * nums[i];
            }

            int[] res = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                // [0,i-1]的乘积 dp1[i]
                // [i+1,len-1]的乘积  dp2[i+1]
                res[i] = dp1[i] * dp2[i + 1];
            }
            return res;
        }
    }
}
