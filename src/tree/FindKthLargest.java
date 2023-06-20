package tree;

/**
 * 215. 数组中的第K个最大元素[medium]
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class FindKthLargest {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int left=0;
            int right = nums.length-1;
            // 排序后，第k大的下标是n-k
            int index = nums.length - k;
            // 二分查找
            while (left<=right){
                // 快速选择
                int p = partition(nums,left,right);
                if (p<index){
                    left=p+1;
                }else if (p>index){
                    right=p-1;
                }else {
                    return nums[p];
                }
            }
            return -1;
        }

        private int partition(int[] nums, int lo, int hi) {
            // 基准数
            int base=nums[lo];

            int left=lo,right=hi;
            while (left<right){
                while (left<right && nums[right]>=base){
                    right--;
                }
                while (left<right && nums[left]<=base){
                    left++;
                }
                // 交换
                int num = nums[left];
                nums[left]=nums[right];
                nums[right]=num;
            }
            // left right相遇
            nums[lo]=nums[left];
            nums[left]=base;

            return left;
        }
    }
}
