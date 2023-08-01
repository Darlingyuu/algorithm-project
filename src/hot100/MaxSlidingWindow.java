package hot100;

import java.util.*;

/**
 * 239. 滑动窗口最大值[hard]
 * https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxSlidingWindow {

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            // 最大堆PriorityQueue,会超时

            // 使用单调递减的双向队列来维护窗口
            MonotonicQueue window = new MonotonicQueue();
            // 记录结果
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (i<k-1){
                    //先填满窗口的前 k - 1
                    window.push(nums[i]);
                }else {
                    // 窗口向前滑动，加入新数字
                    window.push(nums[i]);
                    // 获取当前窗口的最大值
                    list.add(window.max());
                    // 移出旧数字
                    window.pop(nums[i - k + 1]);
                }
            }

            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i]=list.get(i);
            }
            return res;

        }
    }

    // 实现单调递减的双向队列，队头最大，队尾最小
    class MonotonicQueue{
        private LinkedList<Integer> maxq=new LinkedList();

        //  向队尾中添加元素
        public void push(int n){
            // 从队尾一直向前找，比该元素小的就删除
            while (!maxq.isEmpty() && maxq.peekLast()<n){
                maxq.removeLast();
            }
            // 将该元素加入队尾
            maxq.addLast(n);
        }

        // 获取队列中的最大值
        public int max(){
            // 单调递减队列，最大值就是队头的元素
            return maxq.getFirst();
        }

        // 在队头删除指定元素
        public void pop(int n){
            // 该元素最大可能是队头的最大元素，若不是，那在最大元素加入队尾时，该元素就被删除了
            // 此处需判断，可能该元素已经不是队头元素了
            if (n==maxq.getFirst()){
                maxq.removeFirst();
            }
        }
    }
}
