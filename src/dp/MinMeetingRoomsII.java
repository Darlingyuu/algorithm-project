package dp;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253.会议室II[medium]
 * 给你输入若干形如 [begin, end] 的区间，代表若干会议的开始时间和结束时间，请你计算至少需要申请多少间会议室
 */
public class MinMeetingRoomsII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] meetings={{0,30},{5,10},{15,20}};
        System.out.println(solution.minMeetingRooms(meetings));
    }

    static class Solution{
        // 扫描线
        public int minMeetingRoomsII(int[][] meetings){
            int n = meetings.length;
            //记录每个会议的开始时间
            int[] start = new int[n];
            //记录每个会议的结束时间
            int[] end = new int[n];
            for (int i = 0; i < n; i++) {
                start[i]=meetings[i][0];
                end[i]=meetings[i][1];
            }
            //将开始时间和结束时间进行一个升序排列
            Arrays.sort(start);
            Arrays.sort(end);

            //通过扫描线去扫描所有的开始和结束时间，然后如果遇到一个开始时间就让count++,
            // 遇到一个结束时间就让count--，最后看还剩下几个count，剩下的count数量就是同时有几个会议在开，也就是需要的会议室数量
            int count = 0;
            //i和j是双指针
            int i = 0, j = 0;
            int res=0;
            while (i<n && j<n){
                //有新会议开始，并且正好没有会议结束,重新开一个会议室
                if (start[i]<end[j]){
                    count++;
                    i++;
                }else { // 有一个会议结束
                    count--;
                    j++;
                }
                //记录每轮的最大值
                res=Math.max(res,count);
            }

            return res;
        }

        // 优先队列
        public int minMeetingRooms(int[][] meetings){
            // 按照 开始时间 对会议进行排序
            Arrays.sort(meetings,(a,b)->{
                if (a[0]>b[0]) return 1;
                if (a[0]<b[0]) return -1;
                return 0;
            });

            // 记录使用的会议室的结束时间
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.add(meetings[0][1]);
            for (int i = 1; i < meetings.length; i++) {
                int start = meetings[i][0];
                if (start>=queue.peek()){ // 堆顶的会议已经结束，可以使用同一个会议室
                    // 弹出栈顶
                    queue.poll();
                }
                // 加入最新会议信息
                queue.offer(meetings[i][1]);
            }
            return queue.size();
        }
    }
}
