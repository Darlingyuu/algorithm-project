package daily;

/**
 * 2432. 处理用时最长的那个任务的员工[easy]
 * https://leetcode.cn/problems/the-employee-that-worked-on-the-longest-task/
 * 20230505
 */
public class HardestWorker {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] logs = {{0,3},{2,5},{0,9},{1,15}};
//        int[][] logs = {{1,1},{3,7},{2,12},{7,17}};
//        int[][] logs = {{0,10},{1,20}};
        solution.hardestWorker(10,logs);
    }

    static class Solution {
        public int hardestWorker(int n, int[][] logs) {
            if (n<2) return 0;
            // 记录最长勇士
            int time=-1;
            // 记录最长用时的最小员工id
            int id=-1;

            for (int i = 0; i < logs.length; i++) {
                if (i==0){
                    time=logs[0][1];
                    id=logs[0][0];
                }else {
                    int newTime = logs[i][1] - logs[i - 1][1];
                    if (time<newTime || (time==newTime && id>=logs[i][0])){
                        time=newTime;
                        id=logs[i][0];
                    }
                }
            }
            System.out.println(time);
            System.out.println(id);
            return id;
        }
    }
}
