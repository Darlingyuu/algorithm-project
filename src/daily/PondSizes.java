package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 面试题 16.19. 水域大小[medium]
 * https://leetcode.cn/problems/pond-sizes-lcci/
 */
public class PondSizes {
    public static void main(String[] args) {
        int[][] land={{0,2,1,0},{0,1,0,1},{1,1,0,1},{0,1,0,1}};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.pondSizes(land)));
    }

    static class Solution {
        int m;
        int n;
        public int[] pondSizes(int[][] land) {
            m = land.length;
            n = land[0].length;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (land[i][j]==0){
                        list.add(bfs(land,i,j));
                    }
                }
            }
            int[] ans = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i]=list.get(i);
            }
            Arrays.sort(ans);
            return ans;
        }

        int[][] dir={{1,0},{-1,0},{0,-1},{0,1},{-1,1},{-1,-1},{1,1},{1,-1}};
        private Integer bfs(int[][] land, int x, int y) {
            LinkedList<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x,y});
            land[x][y]=1;
            int count=1;
            while (!queue.isEmpty()){
                int[] cur = queue.poll();
                for (int i = 0; i < 8; i++) {
                    int dx = cur[0]+dir[i][0];
                    int dy = cur[1]+dir[i][1];
                    if (dx>=0 && dx<m && dy>=0 && dy<n &&land[dx][dy]==0){
                        queue.offer(new int[]{dx,dy});
                        land[dx][dy]=1;
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
