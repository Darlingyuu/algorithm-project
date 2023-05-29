package array;

import java.util.Random;

/**
 * 528. 按权重随机选择[medium]
 * https://leetcode.cn/problems/random-pick-with-weight/
 */
public class PickIndex {
    public static void main(String[] args) {
        int[] w={1,3};
        Solution solution = new Solution(w);
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
    }

    static class Solution {
        // 将权重转换成前缀和数组
        private int[] preSum;
        private Random random=new Random();


        public Solution(int[] w) {
            preSum=new int[w.length+1];
            for (int i = 0; i < w.length; i++) {
                preSum[i+1]=preSum[i]+w[i];
            }

        }

        public int pickIndex() {
            // [1,preSum[preSum.length - 1]+1)
            int num =  random.nextInt(preSum[preSum.length - 1]) + 1;
            return binarySearch(preSum,num)-1;
        }

        private int binarySearch(int[] preSum, int num) {
            // 下标0位置未计数
            // [ )
            int left=1,right=preSum.length;
            while (left<right){
                int mid=left+(right-left)/2;
                if (preSum[mid]==num){
                    return mid;
                }else if (preSum[mid]>num){
                    right=mid; // 收缩右边界
                }else if (preSum[mid]<num){
                    left=mid+1; // 收缩左边界
                }
            }
            return left;
        }
    }
}
