package graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 210. 课程表 II[medium]
 * https://leetcode.cn/problems/course-schedule-ii/
 */
public class FindOrder {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findOrder(numCourses, prerequisites)));
    }

    static class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 建图{邻接表）
            LinkedList<Integer>[] graph=buildGraph(numCourses,prerequisites);

            // 构建入度数组
            int[] indegree = new int[numCourses];
            for (int[] edge : prerequisites) {
                //edge[1] -> edge[0]
                int to = edge[0];
                // to入度增加
                indegree[to]++;
            }

            LinkedList<Integer> queue = new LinkedList<>();
            // 将入度为0的节点添加到队列中
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i]==0){
                    queue.offer(i);
                }
            }

            // 记录拓扑排序结果
            int[] res = new int[numCourses];
            // 记录比那里节点的顺序（索引
            int count=0;

            // BFS循环
            while (!queue.isEmpty()){
                //当前节点
                Integer cur = queue.poll();
                // 记录结果
                res[count]=cur;
                count++;
                // 遍历cur指向的节点
                for (Integer next : graph[cur]) {
                    // 入度减1
                    indegree[next]--;
                    // 如果入度为0加入队列
                    if (indegree[next]==0){
                        queue.offer(next);
                    }
                }
            }

            if (count!=numCourses){
                // 存在环，拓扑排序不存在
                return new int[]{};
            }

            return res;
        }

        // 建图{邻接表）
        private LinkedList<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            LinkedList<Integer>[] graph=new LinkedList[numCourses];
            // 初始化
            for (int i = 0; i < numCourses; i++) {
                graph[i]=new LinkedList<>();
            }

            for (int[] edge : prerequisites) {
                //edge[1] -> edge[0]
                int from = edge[1];
                int to = edge[0];
                // 添加有向边 from->to
                graph[from].add(to);
            }
            return graph;
        }
    }
}
