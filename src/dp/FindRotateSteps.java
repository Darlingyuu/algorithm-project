package dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 514. 自由之路[hard]
 * https://leetcode.cn/problems/freedom-trail/
 */
public class FindRotateSteps {
    class Solution {
        int[][] memo;
        // 字符 -> 索引列表
        HashMap<Character, List<Integer>> charToIndex;
        public int findRotateSteps(String ring, String key) {
            int m = ring.length();
            int n = key.length();
            memo = new int[m][n];

            // 记录圆环上字符到索引的映射
            // 字符 -> 索引列表
            charToIndex = new HashMap<>();
            for (int i = 0; i < m; i++) {
                char c = ring.charAt(i);
                if (!charToIndex.containsKey(c)) {
                    charToIndex.put(c, new LinkedList<>());
                }
                charToIndex.get(c).add(i);
            }

            // 圆盘指针最初指向 12 点钟方向，
            // 从第一个字符开始输入 key
            return dp(ring,0,key,0);
        }

        /**
         * 采用i,j指针来分别标记圆盘和输入字符串的位置
         * 但是当j到达末尾，不发确定i的位置
         * 只知道i一开始在ring[0]
         * 所以定义dp：
         *   当圆盘指针指向 ring[i] 时，输入字符串 key[j..] 至少需要 dp(ring, i, key, j) 次操作。
         * 那么结果就是:
         *   当圆盘指针指向 ring[0] 时，输入字符串 key[0...] 至少需要 dp(ring,0,key,0)次操作。
         */

        // 当圆盘指针指向 ring[i] 时，输入字符串 key[j..] 至少需要 dp(ring, i, key, j) 次操作。
        private int dp(String ring, int i, String key, int j) {
            // base case 完成输入
            if (j==key.length()) return 0;
            // 查找备忘录，避免重叠子问题
            if (memo[i][j] != 0) return memo[i][j];

            int n = ring.length();
            // 做选择，此时要输入的字符为key[j]
            int res=Integer.MAX_VALUE;
            /**
             * 找到字符 key[j] 在 ring 中的所有索引
             * 计算一年通过顺时针/逆时针到达每个索引需要的操作数的最小值
             */
            for (Integer k : charToIndex.get(key.charAt(j))) {
                // 当前指针位置i与目标指针位置k之间的距离
                int delta = Math.abs(k - i);
                // 选择顺时针还是逆时针（圆）
                delta=Math.min(delta,n-delta);
                // 将指针拨到 ring[k]，继续输入 key[j+1..]
                int subProblem = dp(ring, k, key, j + 1);
                // 选择「整体」操作次数最少的
                // 加一是因为按动按钮也是一次操作
                res=Math.min(res,1+delta+subProblem);
            }
            return memo[i][j]=res;
        }
    }
}
