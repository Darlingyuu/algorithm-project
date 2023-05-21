package daily;

import java.util.Arrays;

/**
 * LCP 33. 蓄水[easy]
 * https://leetcode.cn/problems/o8SXZn/
 */
public class StoreWater {
    public static void main(String[] args) {
//        int[] bucket={9,0,1};
//        int[] vat={0,2,2};
        int[] bucket={16,29,42,70,42,9};
        int[] vat={89,44,50,90,94,91};
        Solution solution = new Solution();
        System.out.println(solution.storeWater(bucket, vat));
    }

    static class Solution {

        /**
         * 题目中涉及两个操作：升级水桶、蓄水。
         * 我们应该贪心地把升级水桶的操作放在前面，这样在蓄水时，每次能蓄水的量就会更多，操作次数就会更少。
         *
         * 1. 首先，如果最低蓄水量 vat 中所有元素都为 0，说明不需要蓄水，直接返回 0 即可。
         * 2. 接下来，我们可以枚举蓄水的次数 x，其中 x∈[1,max(vat)],
         * 3. 在开始蓄水前，第i个水缸需要的水vat[i]，升级前第i个水桶的容量 bucket[i], 升级后要蓄水x次
         *    因此，每个水桶的升级次数为 max(0,(vat[i]/x)-bucket[i]) 向上取整 记为vatn
         * 4. 我们将所有水桶的升级次数累加，记为 y，再加上蓄水的次数 x，就是总的操作次数。答案为所有 x+y中的最小值。
         */
        public int storeWater(int[] bucket, int[] vat) {
            // 获取vat最大值
            int maxVat = Arrays.stream(vat).max().getAsInt();
            if (maxVat==0) return  0;

            int n=vat.length;
            int ans=Integer.MAX_VALUE;
            // 枚举蓄水的次数 x
            for (int x = 1; x <= maxVat; x++) {
                // 所有水桶的升级次数累加
                int y=0;
                // 当蓄水次数为x次时，遍历每个水桶，计算升级次数
                for (int i = 0; i < n; i++) {
                    // 第i个水缸需要的水 vat[i]
                    // 升级前第i个水桶的容量 bucket[i]
                    // 升级后蓄水x次，那需要升级的次数为  vat[i]/x 向上取整
                    y+=Math.max(0,(vat[i]%x==0?vat[i]/x:vat[i]/x+1)-bucket[i]);
                }
                ans=Math.min(ans,x+y);
            }
            return ans;
        }
    }
}
