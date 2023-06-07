package daily;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2611. 老鼠和奶酪[medium]
 * https://leetcode.cn/problems/mice-and-cheese/
 */
public class MiceAndCheese {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] reward1 = {54,59,94,87,32,10,55,44,21,73,12,70,89,49,13,34,78,20,20,75,90,44,48,74,78,32,70,76,79,49,50,69};
//        int[] reward2 = {78,43,69,22,32,67,65,38,51,4,21,27,82,61,12,85,62,60,67,16,22,3,5,16,13,35,13,41,72,85,20,54};
//        int k = 17;
        int[] reward1={1,1,3,4};
        int[] reward2={4,4,1,1};
        int k=2;
        System.out.println(solution.miceAndCheese(reward1, reward2, k));
    }

    static class Solution {
        public int miceAndCheeseI(int[] reward1, int[] reward2, int k) {
            int ans=0;
            int n = reward1.length;
            // 最小堆
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                // 假设都被第二只老鼠吃掉
                ans += reward2[i];
                // 若i位置被第一只老鼠吃掉，引起的得分变化存入pq中
                pq.offer(reward1[i] - reward2[i]);
                // 堆中只保留k个较大的,最小堆 去掉对顶
                if (pq.size()>k) {
                    pq.poll();
                }
            }

            // 得出最后结果
            while (!pq.isEmpty()){
                ans+=pq.poll();
            }
            return ans;
        }

        /**
         * 如果n块奶酪都被第二只老鼠吃掉，则得分为数组reward2的元素之和，记为 sum
         * 如果下标i处的奶酪被第一只老鼠吃掉，则得分的变化量是diff[i]=reward1[i]−reward2[i]
         *
         */
        public int miceAndCheese(int[] reward1, int[] reward2, int k) {
            int ans = 0;
            int n = reward1.length;
            // diff[i]=reward1[i]−reward2[i]
            int[] diffs = new int[n];
            for (int i = 0; i < n; i++) {
                ans += reward2[i];
                diffs[i] = reward1[i] - reward2[i];
            }
            // 遍历完毕，此时ans为数组reward2的元素之和
            // diff为得分变化
            // 从小到达排序
            Arrays.sort(diffs);
            // 取出较大的k个，即这k个对应的位置i由第一个老鼠吃掉，分数会变化
            for (int i = 1; i <= k; i++) {
                ans += diffs[n - i];
            }
            return ans;
        }
    }
}
