package array;

import java.util.Arrays;

/**
 * 410. 分割数组的最大值[hard]
 * https://leetcode.cn/problems/split-array-largest-sum/
 */
public class SplitArray {
    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        Solution solution = new Solution();
        System.out.println(solution.splitArray(nums, 2));
    }

    static class Solution {
        public int splitArray(int[] nums, int k) {
            // 分成非空数组
            // 最小就是每个一组，各自和的最大值就是数组中的最大数
            // 最大就是所有一组，和最大值就是数组和
            int min = Arrays.stream(nums).max().getAsInt();
            int max = Arrays.stream(nums).sum();

            // 二分搜索，找满足条件的左边界
            // [ )
            int left=min,right=max+1;
            while (left<right){
                int mid=left+(right-left)/2;
                if (isSatisfy(nums,mid,k)){ // mid能满足，收缩右边界
                    right=mid;
                }else {// 不能满足，收缩左边界
                    left=mid+1;
                }
            }
            return left;
        }

        private boolean isSatisfy(int[] nums, int mid, int k) {
            int sum=0;
            int n=1;
            for (int i = 0; i < nums.length; i++) {
                if (sum+nums[i]>mid){
                    n++;
                    sum=nums[i];
                }else {
                    sum+=nums[i];
                }
            }
            return n<=k;
        }
    }
}
