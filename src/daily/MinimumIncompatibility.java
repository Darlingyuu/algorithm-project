package daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 1681. 最小不兼容性[hard]
 * https://leetcode.cn/problems/minimum-incompatibility/
 * https://leetcode.cn/problems/minimum-incompatibility/solutions/2319475/zui-xiao-bu-jian-rong-xing-by-leetcode-s-iiac/
 */
public class MinimumIncompatibility {
    class Solution {
        public int minimumIncompatibility(int[] nums, int k) {
            int n = nums.length; // 最大为16
            // 每个子集的元素个数
            int group = n / k;
            int inf = Integer.MAX_VALUE;

            // dp[mask] 表示已经分配过的元素集合的不兼容性之和
            int[] dp = new int[1 << n];
            // 初始化为最大整数，尚未确定最小不兼容性
            Arrays.fill(dp, inf);
            // 表示空子集
            dp[0] = 0;

            /**
             * 预处理 所有元素个数为group的子集组合
             */
            // key为用来表示哪些元素的二进制数字 value为最大值和最小值的差值
            // 保存所有元素为group个的子集组合
            HashMap<Integer, Integer> values = new HashMap<>();

            // 从 1到 (1<<n)−1依次遍历mask所有状态
            for (int mask = 1; mask < (1 << n); mask++) {
                // mask表示的二进制数中含有group个1,才可以成为一个子集，否则继续增加元素个数即mask++;
                if (Integer.bitCount(mask) != group) continue;

                // 此时mask表示的二进制数中含有group个1，表示有group个元素
                // 记录该子集最小值最大值
                int min = 20, max = 0;
                // 保存当前子集的元素
                HashSet<Integer> cur = new HashSet<>();
                for (int i = 0; i < n; i++) {
                    // 遍历mask中每个元素 mask表示的二进制第i位为1
                    if ((mask & (1 << i)) > 0) {
                        // 保证相等元素只保留最后出现的一个
                        if (cur.contains(nums[i])){
                            break;
                        }
                        cur.add(nums[i]);
                        min=Math.min(min,nums[i]);
                        max=Math.max(max,nums[i]);
                    }
                }

                if (cur.size()==group){
                    values.put(mask,max-min);
                }
            }

            /**
             * 从1到 (1<<n)−1依次遍历 mask所有状态。
             * 对于每一个状态，求出还没有被分配的元素集合 sub
             * 并且保证相等元素只保留最后出现的一个。
             * 再遍历 sub的子集 nxt，检查是否符合条件，作为下一组划分的元素集合
             */
            for (int mask = 0; mask < (1 << n); mask++) {
                if (dp[mask] == inf) {
                    continue;
                }

                // 存放未分配的元素
                // key为元素，value为该元素在nums中的下标
                HashMap<Integer, Integer> seen = new HashMap<>();
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) == 0) {
                        seen.put(nums[i], i);
                    }
                }

                // 未分配的元素个数不足以新划分一个子集，说明该情况不符合，跳过
                if (seen.size()<group){
                    continue;
                }

                // 划分下一组子集
                int sub=0;
                for (Integer v : seen.values()) {
                    sub |=(1<<v); // seen表示的元素全部使用异或方式用二进制方式表示到sub中
                }
                // 用来表示sub的子集
                int nxt = sub;
                while (nxt>0){
                    if (values.containsKey(nxt)) {  // nxt可以作为一组子集
                        dp[mask | nxt] = Math.min(dp[mask | nxt], dp[mask] + values.get(nxt));
                    }
                    // nxt是sub的非空子集
                    nxt = (nxt - 1) & sub;
                }

            }
            return (dp[(1 << n) - 1] < inf) ? dp[(1 << n) - 1] : -1;
        }
    }
}
