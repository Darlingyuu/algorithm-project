package array;

/**
 * 27. 移除元素[easy]
 * https://leetcode.cn/problems/remove-element/
 */
public class RemoveElement {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[] nums ={3,2,2,3};
//        int i = solution.removeElement(nums, 3);

        int[] nums ={0,1,2,2,3,0,4,2};
        int i = solution.removeElement(nums, 2);
        System.out.println(i);
    }

    static class Solution {
        public int removeElement(int[] nums, int val) {
            if (nums.length==0) return 0;
            int p=0,q=0;
            while (q<nums.length){
                if (nums[q]!=val){
                    nums[p]=nums[q];
                    p++;
                }
                q++;
            }
            return p;
        }
    }
}
