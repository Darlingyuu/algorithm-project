package daily;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 1093. 大样本统计[medium]
 * https://leetcode.cn/problems/statistics-from-a-large-sample/
 */
public class SampleStats {
    public static void main(String[] args) {
        int[] count={0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Solution solution = new Solution();
        double[] doubles = solution.sampleStats(count);
        System.out.println(Arrays.toString(doubles));
    }

    static class Solution {
        public double[] sampleStats(int[] count) {
            // 样本数
            int total = Arrays.stream(count).sum();
            int left=0;
            int right=0;
            left=(total+1)/2;
            right=(total+2)/2;

            double minimum=Double.MAX_VALUE;
            double maximum=Double.MIN_VALUE;
            double mean=0.0;
            // 中位数
            double median=0.0;
            // 众数
            double mode=0.0;
            // 众数个数
            int modeCount=0;
            // 统计了的元素个数
            int countSum=0;
            // 总值  注意溢出
            long valueSum=0;
            LinkedList<Integer> queue = new LinkedList<>();
            for (int i = 0; i < count.length; i++) {
                int num = count[i];
                if (num>0){
                    minimum=Math.min(minimum,i);
                    maximum=Math.max(maximum,i);
                    if (num>modeCount){
                        modeCount=num;
                        mode=i;
                    }
                    valueSum+=(long)num*i; // 注意int溢出
                    //找到中位数右坐标
                    if (countSum<right && countSum+num>=right){
                        median+=i;
                    }
                    //找到中位数左坐标
                    if (countSum<left && countSum+num>=left){
                        median+=i;
                    }
                    countSum+=num;
                }
            }


            return new double[]{minimum,maximum,(double)valueSum/total,median/2.0,mode};
        }
    }
}
