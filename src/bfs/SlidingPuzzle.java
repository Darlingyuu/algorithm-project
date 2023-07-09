package bfs;

import java.util.*;

/**
 * 773. 滑动谜题[hard]
 * https://leetcode.cn/problems/sliding-puzzle/
 */
public class SlidingPuzzle {
    class Solution {
        public int slidingPuzzle(int[][] board) {
            // 2x3
            int m=2,n=3;
            StringBuilder sb = new StringBuilder();
            String target="123450";

            // 将2x3的数组转换为字符串作为bfs的起点
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
            }
            String start = sb.toString();

            // 记录一维字符串的相邻索引
            // 0 1 2
            // 3 4 5
            // neighbor[i]表示board中下标为i的邻居
            // 此处可使用下面的generateNeighborMapping()方法来生成
            int[][] neighbor=new int[][]{
                    {1,3},
                    {0,4,2},
                    {1, 5},
                    {0, 4},
                    {3, 1, 5},
                    {4, 2}
            };

            /******* BFS 算法框架开始 *******/
            Queue<String> q = new LinkedList<>();
            // 用来记录已经走过的字符串
            HashSet<String> visited = new HashSet<>();
            // 从起点开始
            q.offer(start);
            visited.add(start);
            // 记录步数
            int step=0;
            while (!q.isEmpty()){
                int size = q.size();
                // 对所有情况进行遍历
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    // 判断是否达到目标局面
                    if (target.equals(cur)){
                        return step;
                    }
                    // cur不是目标，在cur基础上继续移动
                    // 找到数字0的索引
                    int idx = cur.indexOf("0");
                    // 将数字 0 和相邻的数字交换位置
                    for (int adj : neighbor[idx]) {
                        String new_board = swap(cur.toCharArray(), adj, idx);
                        // 防止走回头路
                        if (!visited.contains(new_board)){
                            q.offer(new_board);
                            visited.add(new_board);
                        }
                    }
                }
                step++;
            }
            /******* BFS 算法框架结束 *******/
            return -1;
        }

        private String swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            return new String(chars);
        }

        /**
         * 如果二维数组中的某个元素 e 在一维数组中的索引为 i，
         * 那么 e 的左右相邻元素在一维数组中的索引就是 i - 1 和 i + 1，
         * 而 e 的上下相邻元素在一维数组中的索引就是 i - n 和 i + n，
         * 其中 n 为二维数组的列数
         */
        // 生成neighbor索引映射
        private int[][] generateNeighborMapping(int m, int n) {
            int[][] neighbor = new int[m * n][];
            for (int i = 0; i < m * n; i++) {
                List<Integer> neighbors = new ArrayList<>();

                // 如果不是第一列，有左侧邻居
                if (i % n != 0) neighbors.add(i - 1);
                // 如果不是最后一列，有右侧邻居
                if (i % n != n - 1) neighbors.add(i + 1);
                // 如果不是第一行，有上方邻居
                if (i - n >= 0) neighbors.add(i - n);
                // 如果不是最后一行，有下方邻居
                if (i + n < m * n) neighbors.add(i + n);

                // Java 语言特性，将 List 类型转为 int[] 数组
                neighbor[i] = neighbors.stream().mapToInt(Integer::intValue).toArray();
            }
            return neighbor;
        }

    }
}
