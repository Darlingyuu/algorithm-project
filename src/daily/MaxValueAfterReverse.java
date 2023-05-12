package daily;

/**
 * 1330. 翻转子数组得到最大的数组值[hard]
 * 20230512
 * https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/
 */
public class MaxValueAfterReverse {
    public static void main(String[] args) {
        int[] nums ={2,4,9,24,2,1,10};
        Solution solution = new Solution();
        int i = solution.maxValueAfterReverse(nums);
        System.out.println(i);
    }

    static class Solution {
        public int maxValueAfterReverse(int[] nums) {
            /*
                只计算头尾改变导致的变化值
                翻转前： nums[i-1]  nums[i]  nums[j]  nums[j+1]
                假设根据值从小到大排序： a b c d  (两两组合 b-a d-c | c-a d-b | d-a c-b)
                分情况讨论：
                    1. |nums[i-1]-nums[i]|=b-a  |nums[j]-nums[j+1]|=d-c  (nums[j] nums[j+1]   >  nums[i-1] nums[i])
                        这种情况下，翻转前的头尾差值和为b-a+d-c,
                        翻转后的值为|nums[j]-nums[i-1]|+|nums[i]-nums[j+1]|=nums[j]-nums[i-1]+nums[j+1]-nums[i]=c+d-a-b
                        change=(c+d-a-b)-(b-a+d-c)=2(c-b)
                    2. |nums[i-1]-nums[i]|=d-c  |nums[j]-nums[j+1]|=b-a
                       翻转后的值为|nums[j]-nums[i-1]|+|nums[i]-nums[j+1]|=nums[i-1]-nums[j]+nums[i]-nums[j+1]=c+d-a-b
                       change=(c+d-a-b)-(d-c+b-a)=2(c-b)
                    3. |nums[i-1]-nums[i]|=c-a  |nums[j]-nums[j+1]|=d-b
                        翻转后的值为|nums[j]-nums[i-1]|+|nums[i]-nums[j+1]|，可能性中最大值为d-a+c-b
                       change最大值为=(d-a+c-b)-(c-a+d-b)=0,所以这种情况无需考虑
                    4. |nums[i-1]-nums[i]|=d-b  |nums[j]-nums[j+1]|=c-a ，同3
                    5. |nums[i-1]-nums[i]|=d-a  |nums[j]-nums[j+1]|=c-b ，同3
                    6. |nums[i-1]-nums[i]|=c-b  |nums[j]-nums[j+1]|=d-a ，同3
                    综上，只有两种情况反转后change是正数，为 2(c-b)
             */


            //i位置的数为翻转子数组的头，尾依次后移
            // 由于数组值只会因为翻转子数组的头尾数字印象，计算头尾改变导致的变化值
            int sum=0;

            for (int i = 0; i < nums.length-1; i++) {
                sum+=Math.abs(nums[i]-nums[i+1]);
            }

            //记录最大的变化值
            int mx1=0;
            for (int i = 1; i < nums.length-1; i++) {
                //o位置为子数组头，i位置为子数组尾，记录最大变化值
                mx1=Math.max(mx1,Math.abs(nums[0]-nums[i+1])- Math.abs(nums[i] - nums[i + 1]));
                //i位置为子数组头，nums.length-1位置为子数组尾，计算头尾改变导致的变化值
                mx1=Math.max(mx1,Math.abs(nums[i]-nums[nums.length-1])- Math.abs(nums[i] - nums[i + 1]));
            }

            // 中间位置的子数组
            int mx2 = Integer.MIN_VALUE, mn2 = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length-1; i++) {
                // |nums[i-1]-nums[i]|=b-a  |nums[j]-nums[j+1]|=d-c
                // |nums[i-1]-nums[i]|=d-c  |nums[j]-nums[j+1]|=b-a
                int x = nums[i], y = nums[i + 1];
                // x,y中的较小者为c，mx2记下最大的c
                mx2=Math.max(mx2,Math.min(x,y));
                // x,y中的较大者为b，mn2记下最小的b
                mn2=Math.min(mn2,Math.max(x,y));
            }
            // 2(c-b)
            return sum+Math.max(mx1,2*(mx2-mn2));



            //暴力枚举   O(n2)
//            for (int i = 0; i < nums.length; i++) {
//                if (i<nums.length-1) {
//                    sum += Math.abs(nums[i] - nums[i + 1]);
//                }
//                for (int j = i+1; j < nums.length; j++) {
//                    //翻转前
//                    int reversePre=Math.abs(i==0?0:nums[i-1]-nums[i])+Math.abs(j==nums.length-1?0:nums[j]-nums[j+1]);
//                    //翻转后
//                    int reverseAfter=Math.abs(i==0?0:nums[j]-nums[i-1])+Math.abs(j==nums.length-1?0:nums[i]-nums[j+1]);
//                    change=Math.max(change,reverseAfter-reversePre);
//                }
//            }
//            return sum+change;
        }
    }
}
