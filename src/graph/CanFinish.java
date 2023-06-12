package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表[medium]
 * https://leetcode.cn/problems/course-schedule/
 */
public class CanFinish {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        Solution solution = new Solution();
        System.out.println(solution.canFinish(numCourses, prerequisites));
    }
    // BFS
    static class Solution {
        //  prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。  bi-> ai
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 建图(邻接表)
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);

            // 构建入度数组
            int[] indegree = new int[numCourses];
            for (int[] edge : prerequisites) {
                // edge[1] ->  edge[0]
                int to = edge[0];
                // to入度增加
                indegree[to]++;
            }

            // 根据入度初始化队列中的节点
            // 将不依赖其他的课程的加入队列，即入度为0的节点
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    // 节点 i 没有入度，即没有依赖的节点
                    // 可以作为拓扑排序的起点，加入队列
                    q.offer(i);
                }
            }

            // 记录遍历的节点个数
            int count = 0;

            // 开始执行BFS循环
            while (!q.isEmpty()) {
                // 弹出节点 cur
                Integer cur = q.poll();
                // 遍历的节点个数增加
                count++;
                // 得到cur的指向的节点，并将入度减一
                for (Integer next : graph[cur]) {
                    indegree[next]--;
                    // 如果该节点的入度为0，加入队列
                    if (indegree[next] == 0) {
                        // 如果入度变为 0，说明 next 依赖的节点都已被遍历
                        q.offer(next);
                    }
                }
            }

            // 如果所有节点都被遍历过，说明不成环
            return count == numCourses;
        }

        // 建图(邻接表)
        private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            // 图中共numCourses个节点
            LinkedList<Integer>[] graph = new LinkedList[numCourses];
            // 初始化每个节点
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }

            // 构建邻接表
            for (int[] edge : prerequisites) {
                // edge[1] ->  edge[0]
                int from = edge[1];
                int to = edge[0];
                // 添加一条从 from 指向 to 的有向边
                // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
                graph[from].add(to);
            }
            return graph;
        }
    }
}
