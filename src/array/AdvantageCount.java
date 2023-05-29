package array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 870. 优势洗牌[medium]
 * https://leetcode.cn/problems/advantage-shuffle/
 */
public class AdvantageCount {
    public static void main(String[] args) {
        int[] nums1={2,7,11,15};
        int[] nums2={1,10,4,11};
        Solution solution = new Solution();
        int[] ints = solution.advantageCount(nums1, nums2);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] advantageCount(int[] nums1, int[] nums2) {
            // 借助最大堆给num2排序，同事记录下标  [下标，值]
            PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->b[1]-a[1]);
            for (int i = 0; i < nums2.length; i++) {
                queue.offer(new int[]{i,nums2[i]});
            }
            // 从小到大排序
            Arrays.sort(nums1);
            // 最小值下标
            int left=0;
            // 最大值下标
            int right=nums1.length-1;
            int[] res = new int[nums1.length];
            while (!queue.isEmpty()){
                // 重新排列nums1，同一下标位置若nums1的大，就用nums1，否则用最小的
                // 从大到小比较，每次从队列中取出的都是num2s的最大值
                int[] poll = queue.poll();
                // 在nums2中的下标
                int index = poll[0];
                // 值
                int value = poll[1];
                if (nums1[right]>value){
                    res[index]=nums1[right];
                    right--;
                }else {
                    res[index]=nums1[left];
                    left++;
                }
            }
            return res;
        }
    }
}
