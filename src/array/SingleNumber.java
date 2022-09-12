package array;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/12 18:03
 * 只出现一次的数字:给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        Solution solution = new Solution();
        System.out.println(solution.singleNumber(nums));
    }

    static class Solution {
        /**
         * 使用异或运算，将所有值进行异或
         * 异或运算，相异为真，相同为假，所以 a^a = 0 ;0^a = a
         * 因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
            int reduce = 0;
            for (int num : nums) {
                reduce =  reduce ^ num;
            }
            return reduce;
        }
    }
}
