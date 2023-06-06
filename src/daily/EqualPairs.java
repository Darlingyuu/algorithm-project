package daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 2352. 相等行列对[medium]
 * https://leetcode.cn/problems/equal-row-and-column-pairs/
 */
public class EqualPairs {
    public static void main(String[] args) {
        int[][] grid = {{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}};
        Solution solution = new Solution();
        System.out.println(solution.equalPairsI(grid));
    }
    static class Solution {

        // 模拟 时间O(n3) 空间O(1)
        public int equalPairs(int[][] grid) {
            int n = grid.length;
            int count=0;
            for (int i = 0; i < n ; i++) {
                for (int j = 0; j < n; j++) {
                    boolean flag=true;
                    for (int k = 0; k < n; k++) {
                        if (grid[i][k]!=grid[k][j]){
                            flag=false;
                            break;
                        }
                    }
                    if (flag) {
                        count++;
                    }
                }
            }
            return count;
        }

        // 可以把每行和每列的元素放入list中，放入哈希表中  时间O(n2) 空间O(1)
        public int equalPairsI(int[][] grid) {
            int n = grid.length;
            // key=List<Integer>  元素相同的list会被认为是同一个key
            HashMap<List<Integer>, Integer> cnt = new HashMap<List<Integer>, Integer>();
            for (int[] row : grid) {
                List<Integer> arr = new ArrayList<Integer>();
                for (int num : row) {
                    arr.add(num);
                }
                cnt.put(arr, cnt.getOrDefault(arr, 0) + 1);
            }

            int res = 0;
            for (int j = 0; j < n; j++) {
                List<Integer> arr = new ArrayList<Integer>();
                for (int i = 0; i < n; i++) {
                    arr.add(grid[i][j]);
                }
                if (cnt.containsKey(arr)) {
                    res += cnt.get(arr);
                }
            }
            return res;
        }

    }
}
