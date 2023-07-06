package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 2178. 拆分成最多数目的正偶数之和[medium]
 * https://leetcode.cn/problems/maximum-split-of-positive-even-integers/
 */
public class MaximumEvenSplit {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Long> list = solution.maximumEvenSplit(28);
        for (Long l : list) {
            System.out.println(l);
        }
    }
    static class Solution {
        public List<Long> maximumEvenSplit(long finalSum) {
            ArrayList<Long> res = new ArrayList<>();
            if (finalSum%2!=0) return res;

            /**
             * 从最小的偶整数 222 开始依次尝试拆分，直到剩余的数值小于等于当前被拆分的最大偶整数为止
             * 此时，我们已经拆分成尽可能多的偶数，不可能拆分出更多的互不相同的偶数。
             * 如果此时拆分后剩余的 finalSum大于零，则将这个数值加到最大的偶整数上，从而保证所有的数互不相同。
             */
            for (long i = 2; i <=finalSum ; i+=2) {
                res.add(i);
                finalSum-=i;
            }
            res.set(res.size()-1,res.get(res.size()-1)+finalSum);
            return res;
        }

    }
}
