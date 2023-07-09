package daily;

import java.util.PriorityQueue;

/**
 * 2532. 过桥的时间[hard]
 * https://leetcode.cn/problems/time-to-cross-a-bridge/
 */
public class FindCrossingTime {

    class Solution {
        public int findCrossingTime(int n, int k, int[][] time) {
            // time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi]
            /**
             * K个工人
             * 工人共有4中状态：在左侧等待、在右侧等待、在左侧工作(放下所选箱子)、在右侧工作(搬起所选箱子)
             * 用4个优先队列来存放处于每个状态下的工人集合
             *  1. 在左侧等待的工人：wait_left，题目中定义的效率越高，优先级越高。
             *  2. 在右侧等待的工人：wait_right，题目中定义的效率越高，优先级越高。
             *  3. 在左侧工作的工人：work_left，完成时间越早，优先级越高。
             *  4. 在右侧工作的工人：work_right，完成时间越早，优先级越高。
             */

            // 在左侧等待的工人
            // 定义等待中的工人优先级比较规则，时间总和越高，效率越低，越优先被取出
            PriorityQueue<Integer> waitLeft = new PriorityQueue<>((x,y)->{
                int timeX = time[x][0] + time[x][2];
                int timeY = time[y][0] + time[y][2];
                return timeX!=timeY?timeY-timeX:y-x;
            });

            // 在右侧等待的工人
            PriorityQueue<Integer> waitRight = new PriorityQueue<>((x,y)->{
                int timeX = time[x][0] + time[x][2];
                int timeY = time[y][0] + time[y][2];
                return timeX!=timeY?timeY-timeX:y-x;
            });

            // 在左侧工作的工人  it[]{完成工作的时刻，工人id}
            PriorityQueue<int[]> workLeft = new PriorityQueue<>((x,y)->{
                // 完成工作的时刻早的优先，否则工人id小的优先
                if (x[0]!=y[0]){
                    return x[0]-y[0];
                }else {
                    return x[1]-y[1];
                }
            });

            // 在右侧工作的工人
            PriorityQueue<int[]> workRight = new PriorityQueue<>((x,y)->{
                if (x[0]!=y[0]){
                    return x[0]-y[0];
                }else {
                    return x[1]-y[1];
                }
            });

            // 表示右侧还有多少个箱子需要搬运
            int remain=n;
            // 时间
            int curTime=0;
            // 初始化，一开始将所有工人都加入waitLeft
            for (int i = 0; i < k; i++) {
                waitLeft.offer(i);
            }

            // 当 remain>0 时，搬运工作需要继续
            // 题目求解的是最后一个回到左边的工人的到达时间，因此当右侧还有工人在等待或在工作时（即 work_right 或 wait_right不为空），搬运工作就需要继续
            while (remain>0 || !workRight.isEmpty() || !waitRight.isEmpty()){
                // 1. 若 workLeft 或 workRight 中的工人完成工作，则将他们取出，并分别放置到 waitLeft 和 waitRight 中。
                while (!workLeft.isEmpty() && workLeft.peek()[0]<=curTime){
                    waitLeft.offer(workLeft.poll()[1]);
                }
                while (!workRight.isEmpty() && workRight.peek()[0] <= curTime) {
                    waitRight.offer(workRight.poll()[1]);
                }

                if (!waitRight.isEmpty()){
                    // 2. 若右侧有工人在等待，则取出优先级最低的工人并过桥
                    Integer id = waitRight.poll();
                    // 耗时过桥时间
                    curTime+=time[id][2];
                    // 将该工人加入workLeft,左侧工作完成时刻为curTime+time[id][3]
                    workLeft.offer(new int[]{curTime+time[id][3],id});
                } else if (remain > 0 && !waitLeft.isEmpty()) {
                    // 3. 若右侧还有箱子，并且左侧有工人在等待，则取出优先级最低的工人并过桥
                    int id = waitLeft.poll();
                    curTime += time[id][0];
                    workRight.offer(new int[]{curTime + time[id][1], id});
                    remain--;
                }else {
                    // 4. 否则，没有人需要过桥，时间过渡到 workLeft 和 workRight 中的最早完成时间
                    int nextTime = Integer.MAX_VALUE;
                    if (!workLeft.isEmpty()) {
                        nextTime = Math.min(nextTime, workLeft.peek()[0]);
                    }
                    if (!workRight.isEmpty()) {
                        nextTime = Math.min(nextTime, workRight.peek()[0]);
                    }
                    if (nextTime != Integer.MAX_VALUE) {
                        curTime = Math.max(nextTime, curTime);
                    }
                }

            }
            return curTime;
        }
    }
}
