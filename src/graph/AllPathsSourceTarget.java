package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 797. 所有可能的路径[medium]
 * https://leetcode.cn/problems/all-paths-from-source-to-target/
 */
public class AllPathsSourceTarget {
    public static void main(String[] args) {
        int[][] graph={{1,2},{3},{3},{}};
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.allPathsSourceTarget(graph);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
    static class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 记录路径
            LinkedList<Integer> path = new LinkedList<>();
            traverse(graph,0,path);
            return res;
        }

        private void traverse(int[][] graph, int start, LinkedList<Integer> path) {
            // 当前节点加入路径中
            path.add(start);

            // 到达终点
            if (start==graph.length-1) {
                // 将路径存入结果中
                res.add(new ArrayList<>(path));
            }

            // 遍历邻居节点
            for (int i : graph[start]) {
                // 回溯
                traverse(graph,i,path);
            }

            // 离开该节点
            path.removeLast();
        }
    }
}
