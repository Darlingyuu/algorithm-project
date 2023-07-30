package hot100;

/**
 * 11. 盛最多水的容器[medium]
 * https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxArea {

    class Solution {
        public int maxArea(int[] height) {
            // 双指针
            int left = 0;
            int right=height.length-1;
            // 记录最大容量
            int maxArea=0;
            while (left<right){
                // 当前容量
                int curArea = Math.min(height[left], height[right]) * (right - left);
                // 更新最大容量
                maxArea=Math.max(maxArea,curArea);

                // 那边短移动哪边，只有这样才有可能让容量变大
                if (height[left] >= height[right]) {
                    right--;
                } else {
                    left++;
                }
            }
            return maxArea;
        }
    }
}
