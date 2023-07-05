package daily;

/**
 * 2600. K 件物品的最大和[easy]
 * https://leetcode.cn/problems/k-items-with-the-maximum-sum/
 */
public class KItemsWithMaximumSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.kItemsWithMaximumSum(6, 6, 6, 13));
    }

    static class Solution {
        public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
//            if (k<=numOnes){
//                return k;
//            }
//            if (k<=numOnes+numZeros){
//                return numOnes;
//            }
//            if (k<=numNegOnes+numZeros+numNegOnes){
//                // numOnes+numZeros+m=k  m=k-( numOnes+numZeros)
//                // numOnes-m=2numOnes+numZeros)-k
//                return 2*numOnes+numZeros-k;
//            }
//            return 0;

            return k>numOnes+numZeros?2*numOnes+numZeros-k:Math.min(numOnes,k);
        }
    }
}
