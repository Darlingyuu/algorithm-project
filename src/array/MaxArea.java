package array;

/**
 * 11. 盛最多水的容器[medium]
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class MaxArea {
    public static void main(String[] args) {
        int[] height={1,8,6,2,5,4,8,3,7};
        Solution solution = new Solution();
        System.out.println(solution.maxArea(height));
    }
    static class Solution {
        public int maxArea(int[] height) {
            int res=0;
            int left=0,right=height.length-1;
            while (left<right){
                if (height[left]<height[right]){
                    res=Math.max(res,height[left]*(right-left));
                    left++;
                }else {
                    res=Math.max(res,height[right]*(right-left));
                    right--;
                }
            }
            return res;
        }
    }
}
