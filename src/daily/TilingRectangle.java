package daily;

/**
 * 1240. 铺瓷砖[hard]
 * https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares/
 */
public class TilingRectangle {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.tilingRectangle(11, 13));
    }

    static class Solution {
        int ans;
        public int tilingRectangle(int n, int m) {
            ans=Math.max(n,m);
            // 用于标记每个位置是否铺了瓷砖
            boolean[][] rect = new boolean[n][m];
            dfs(0,0,rect,0);
            return ans;
        }

        // 起点下标 (x,y)
        // 记录瓷砖个数 cnt
        private void dfs(int x, int y, boolean[][] rect, int cnt) {
            int n = rect.length;
            int m = rect[0].length;

            // 当前用的瓷砖已经超过了之前完整铺完使用的最少瓷砖个数，就无需继续
            if (cnt>=ans) return;

            // 已经贴满了
            if (x>=n){
                // 更新最新的瓷砖个数（比之前的ans少，否则在上一个判断中就会结束）
                ans=cnt;
                return;
            }

            // 当前行检测完毕，检测下一行
            if (y>=m){
                dfs(x+1,0,rect,cnt);
                return;
            }

            // 如果当前位置被之前的瓷砖覆盖了，直接尝试下一个位置
            if (rect[x][y]){
                dfs(x,y+1,rect,cnt);
                return;
            }

            // 以起点下标(x,y)作为瓷砖左上角，瓷砖最大边长Math.min(n-x,m-y),最小1
            // 从大到小枚举边长
            for (int k =Math.min(n-x,m-y) ; k >=1 && isAvailable(rect, x, y, k); k--) {
                // 将长度为 k 的正方形区域标记覆盖
                fillUp(rect,x,y,k,true);
                // 跳过 k 个位置开始检测
                dfs(x,y+k,rect,cnt+1);
                // 取消标记
                fillUp(rect,x,y,k,false);
            }

        }

        // 标记或取消标记 (x,y)作为瓷砖左上角，边长为k的瓷砖覆盖的位置
        private void fillUp(boolean[][] rect, int x, int y, int k, boolean val) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    rect[x+i][y+j]=val;
                }
            }
        }

        // 判断以(x,y)作为瓷砖左上角，能否刚好铺下一个边长为k的瓷砖
        private boolean isAvailable(boolean[][] rect, int x, int y, int k) {
            // 检查覆盖到的每个位置之前并没有铺
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (rect[x+i][y+j]){
                        return false;
                    }
                }
            }
            return true;
        }
    }

}
