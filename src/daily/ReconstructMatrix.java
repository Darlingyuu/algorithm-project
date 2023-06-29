package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 1253. 重构 2 行二进制矩阵[medium]
 * https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix/
 */
public class ReconstructMatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] colsum={1,1,1};
        List<List<Integer>> lists = solution.reconstructMatrix(2, 1, colsum);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }

    }
    static class Solution {
        public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
            int n = colsum.length;
            // 记录colsum元素总和
            int sum=0;
            // 记录colsum[i]=2的个数
            int tow=0;
            for (int i = 0; i < n; i++) {
                if (colsum[i]==2){
                    tow++;
                }
                sum+=colsum[i];
            }

            // 不合题意
            if (sum!=upper+lower || Math.min(upper,lower)<tow){
                return new ArrayList<>();
            }

            // 减去上下都要填入1的情况
            upper-=tow;
            lower-=tow;
            // 保存结果
            List<List<Integer>> res=new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                res.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                if (colsum[i]==2){
                    // 上下都填入1
                    res.get(0).add(1);
                    res.get(1).add(1);
                }else if (colsum[i]==1){
                    // 只有一行填入1
                    // 当上面一行的upper>0时，优先填入上方，否则填下方
                    if (upper>0){
                        res.get(0).add(1);
                        res.get(1).add(0);
                        upper--;
                    }else {
                        res.get(0).add(0);
                        res.get(1).add(1);
                        lower--;
                    }
                }else {
                    // 都是0
                    res.get(0).add(0);
                    res.get(1).add(0);
                }
            }
            return res;
        }
    }
}
