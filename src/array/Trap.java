package array;

/**
 * 42. 接雨水[hard]
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class Trap {
    public static void main(String[] args) {
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
        Solution solution = new Solution();
        System.out.println(solution.trap(height));
    }

    static class Solution {
        public int trap(int[] height) {
            //[0,i]的最大值
            int lMax=height[0];
            // [i,len-1]的最大值
            int rMax=height[height.length-1];

            int res=0;
            // 双指针
            int left=0,right=height.length-1;
            while (left<right){
                // [0,left]的最大值
                lMax=Math.max(lMax,height[left]);
                // [right,len-1]的最大值
                rMax=Math.max(rMax,height[right]);

                // res += min(l_max, r_max) - height[i]
                if (lMax<rMax) {
                    // left位置可接的雨水
                    res += lMax - height[left];
                    left++;
                }else {
                    // right位置可接的雨水
                    res += rMax - height[right];
                    right--;
                }
            }
            return res;
        }
    }
}
