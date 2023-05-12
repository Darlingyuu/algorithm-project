package array;

/**
 * 26. 删除有序数组中的重复项[easy]
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums={0,0,1,1,1,2,2,3,3,4};
        Solution solution = new Solution();
        int i = solution.removeDuplicates(nums);
        System.out.println(i);
    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length==0 || nums.length==1) return nums.length;
            // 使用快慢指针
            int p=0,q=1;
            while (q<nums.length){
                if (nums[q]!=nums[p]){
                    p++;
                    nums[p]=nums[q];
                }
                q++;
            }
            return p+1;
        }
    }
}
