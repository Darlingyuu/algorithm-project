package array;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/12 18:03
 * :给定一个非空整数数组，只有两种数出现奇数次，其他的数都出现偶数次，找出出现奇数次的这两个数。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class SingleNumberII {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 3, 3, 4, 4};
        Solution solution = new Solution();
        solution.singleNumberII(nums);
    }

    static class Solution {
        /**
         * 假设出现奇数次的两个数是a和b
         * 将所有数进行异或，最终得到的reduce=a^b
         * 因为a b是不同的两个数，reduce！=0，那么reduce必定有一位上面是1，
         * 也就是a,b两个数至少在某一位上面，一个数是1，另一个是0
         * 通过对reduce取反加一，再对自己进行&运算，得到最右侧的1的位置
         * 说明a b在这个位置上一个数是1 另一个是0
         * 将数组中的每个数与该位置1进行&运算，如果等于0，那这些数中必定有a或者b,假设为a
         * 这些数进行异或之后，剩下的就是a
         * 再与reduce异或，a^reduce=a^a^b=b得到另一个数b
         *
         * @param nums
         * @return
         */
        public void singleNumberII(int[] nums) {
            int reduce = 0;
            // 将所有数进行异或 最终得到的reduce=a^b
            for (int num : nums) {
                reduce = reduce ^ num;
            }

            // reduce有一个位置上是1
            // 假设reduce=  1001100
            // ~reduce取反= 0110011
            // ~reduce+1=   0110100
            // reduce & (~reduce+1)= 0000100
            int rightOne = reduce & (~reduce + 1); // 提取出最右侧的1

            int onlyOne = 0;
            for (int num : nums) {
                if ((num & rightOne) == 0) {// 说明num在reduce最右侧的1的位置上是0
                    onlyOne ^= num;
                }
            }

            System.out.println("一个数是:" + onlyOne);
            System.out.println("另一个数是:" + (onlyOne ^ reduce));
        }
    }
}
