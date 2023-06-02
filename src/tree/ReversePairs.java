package tree;

/**
 * 493. 翻转对[hard]
 * https://leetcode.cn/problems/reverse-pairs/
 */
public class ReversePairs {
    public static void main(String[] args) {
        int[] nums={2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        Solution solution = new Solution();
        System.out.println(solution.reversePairs(nums));
    }

    static class Solution {
        private long[] temp;
        private int count;
        public int reversePairs(int[] nums) {
            temp=new long[nums.length];
            long[] newNums=new long[nums.length];
            for (int i = 0; i < nums.length; i++) {
                newNums[i]=nums[i];
            }

            sort(newNums,0,nums.length-1);
            return count;
        }

        private void sort(long[] nums, int low, int high) {
            if (low==high) return;

            int mid = low + (high - low) / 2;
            sort(nums, low, mid);
            sort(nums,mid+1,high);
            merge(nums,low,mid,high);
        }

        private void merge(long[] nums, int low, int mid, int high) {
            for (int i = low; i <= high ; i++) {
                temp[i]=nums[i];
            }

            int p=low,q=mid+1;
            while (p<=mid && q<=high){
                // [p,mid]  都大于2*nums[q]
                if (temp[p]>2*nums[q]){
                    count+=mid-p+1;
                    q++;
                }else {
                    p++;
                }
            }


            // 合并有个有序数组
            int i=low,j=mid+1;
            for (int k = low; k <=high ; k++) {
                if (i==mid+1){
                    nums[k]=temp[j++];
                }else if (j==high+1){
                    nums[k]=temp[i++];
                }else if (temp[i]>temp[j]){
                    nums[k]=temp[j++];
                }else {
                    nums[k]=temp[i++];
                }
            }
        }

    }
}
