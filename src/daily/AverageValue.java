package daily;

/**
 * 2455. 可被三整除的偶数的平均值[easy]
 * https://leetcode.cn/problems/average-value-of-even-numbers-that-are-divisible-by-three/
 */
public class AverageValue {
    public static void main(String[] args) {

    }

    class Solution {
        public int averageValue(int[] nums) {
            int sum=0;
            int count=0;
            for (int num : nums) {
                if (num%3==0 && num%2==0){
                    sum+=num;
                    count++;
                }
            }
            return count==0?0:sum/count;
        }
    }
}
