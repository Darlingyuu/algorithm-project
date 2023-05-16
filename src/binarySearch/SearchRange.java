package binarySearch;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置[medium]
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {
    public static void main(String[] args) {
        int[] nums={1,2,3,3,3,3,4,5,9};
//        int[] nums={5,7,7,8,8,10};
        Solution solution = new Solution();
        int[] ints = solution.searchRange(nums, 3);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] searchRange(int[] nums, int target) {
            //搜索区间 [left,right]
            int left=0,right=nums.length-1;
            while (left<=right){
                int mid=left+(right-left)/2;
                if (nums[mid]==target){
                    //用两个指针分别向前向后寻找边界
                    int p=mid-1;
                    int q=mid+1;
                    while (p>=0 && nums[p]==target) p--;
                    while (q<=nums.length-1 && nums[q]==target) q++;
                    return new int[]{p+1,q-1};
                }else if (nums[mid]<target){
                    left=mid+1;
                }else if (nums[mid]>target){
                    right=mid-1;
                }
            }
            return new int[]{-1,-1};

        }
        public int[] searchRangeI(int[] nums, int target) {
            //搜索区间 [left,right]
            int left=0,right=nums.length-1;

            //找左边界
            while (left<=right){
                int mid=left+(right-left)/2;
                if (nums[mid]==target){
                    right=mid-1;
                }else if (nums[mid]<target){
                    left=mid+1;
                }else if (nums[mid]>target){
                    right=mid-1;
                }
            }
            int leftBound=(left==nums.length|| nums[left]!=target)?-1:left;

            //找右边界
            left=0;right=nums.length-1;
            while (left<=right){
                int mid=left+(right-left)/2;
                if (nums[mid]==target){
                    left=mid+1;
                }else if (nums[mid]<target){
                    left=mid+1;
                }else if (nums[mid]>target){
                    right=mid-1;
                }
            }
            int rightBound=(right==-1|| nums[right]!=target)?-1:right;

            return new int[]{leftBound,rightBound};
        }
    }
}
