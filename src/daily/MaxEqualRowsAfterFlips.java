package daily;

import java.util.HashMap;

/**
 * 1072. 按列翻转得到最大值等行数[medium]
 * https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows/
 */
public class MaxEqualRowsAfterFlips {
    public static void main(String[] args) {
        char i = (char)('0' + '0');
        System.out.println(i);
    }
    //翻转指，将该位置的1变为0，或者0变为1
    class Solution {
        /**
         * 通过翻转某些列的方式得到相等的行(等价行)：
         *  1. 两行的对应位置元素相等，即如果其中一行元素为 1,0,0,1，则另一行元素也为 1,0,0,1；
         *  2. 两行的对应位置元素相反，即如果其中一行元素为 1,0,0,1，则另一行元素为 0,1,1,0。
         *
         *  我们可以遍历矩阵的每一行，将每一行转换成第一个元素为 0 的“等价行”
         *   1. 如果当前行的第一个元素为 000，那么当前行的元素保持不变；
         *   2. 如果当前行的第一个元素为 111，那么我们将当前行的每个元素进行翻转，即 000 变成 111, 111 变成 000。
         *      也就是说，我们将以 111 开头的行翻转成以 000 开头的“等价行”。
         *
         *  这样一来，我们只需要用一个哈希表来统计转换后的每一行的出现次数，
         *  其中键为转换后的行（可以将所有数字拼接成一个字符串），值为该行出现的次数。
         *  最后，哈希表中值的最大值即为矩阵中最多包含等价行的行数
         */
        public int maxEqualRowsAfterFlips(int[][] matrix) {
            HashMap<String, Integer> cnt = new HashMap<>();

            int ans=0,n=matrix[0].length;

            //遍历每一行
            for (int[] row : matrix) {
                char[] cs = new char[n];
                //遍历该行元素
                for (int i = 0; i < n; i++) {
                    // 如果 matrix[i][0] 为 1，则对该行元素进行翻转
                    // i=0, row[0] ^ row[0]=0
                    // row[0]=0时, row[i]=0 row[0] ^ row[i]=0，row[i]=1 row[0] ^ row[i]=1，即0开头的行不用翻转
                    // row[0]=1时, row[i]=0 row[0] ^ row[i]=1，row[i]=1 row[0] ^ row[i]=0，即1开头的行要翻转
                    //最终结果存放在cs中
                    cs[i] = (char) (row[0] ^ row[i]);
                }
                ans = Math.max(ans, cnt.merge(String.valueOf(cs), 1, Integer::sum));
            }

            return ans;
        }
    }
}
