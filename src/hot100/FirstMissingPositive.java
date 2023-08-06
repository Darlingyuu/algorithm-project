package hot100;

/**
 * 41. 缺失的第一个正数[hard]
 * https://leetcode.cn/problems/first-missing-positive/?envType=study-plan-v2&envId=top-100-liked
 */
public class FirstMissingPositive {
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;
            // [1,len],能存储的最多连续
            // 把对应数字放在对顶位置
            // 如果都存在，缺失的第一个正数是len+1
            // 如果哪个不存在，哪个小就是缺失的第一个正数
            for (int i = 0; i < len; i++) {
                // nums[i]应该在[1.len]范围内
                // nums[i]应该存放的位置nums[i]-1
                // 若当前位置下标不是nums[i]-1，则两者交换
                // 继续判断当前位置的数是否是合适，不合适继续
                while  (nums[i] >= 1 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                    int tmp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = tmp;
                }
            }

            for (int i = 0; i < len; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            return len + 1;
        }
    }
}
