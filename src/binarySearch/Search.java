package binarySearch;

/**
 * 704. 二分查找[easy]
 * https://leetcode.cn/problems/binary-search/
 */
public class Search {
    public static void main(String[] args) {

    }

    class Solution {
        public int search(int[] nums, int target) {
            int left=0,right=nums.length-1;

            while (left<=right){
                int mid=left+(right-left)/2;
                if (nums[mid]==target){
                    return mid;
                }else if (nums[mid]<target){
                    left=mid+1;
                }else if (nums[mid]>target){
                    right=mid-1;
                }
            }
            return -1;
        }
    }
}
