package daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 1090. 受标签影响的最大值[medium]
 * https://leetcode.cn/problems/largest-values-from-labels/
 */
public class LargestValsFromLabels {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] values={4,6,3,9,2};
        int[] labels={2,0,0,0,2};
        System.out.println(solution.largestValsFromLabels(values, labels, 5, 2));
    }

    static class Solution {
        public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
            ArrayList<Num> list = new ArrayList<>();
            for (int i = 0; i < values.length; i++) {
                list.add(new Num(values[i],labels[i]));
            }

            Collections.sort(list);
            // 存放已经有的label及个数
            HashMap<Integer,Integer> map = new HashMap<>();
            // 记录和
            int sum=0;
            for (int i = 0; i < list.size(); i++) {
                Num num = list.get(i);
                // 标签不同，可计入结果标签相同 相同标签的个数未达到useLimit,可选择该元素标签相同 相同标签的额度还有,可选择该元素
                if (!map.containsKey(num.label) || (map.get(num.label) < useLimit)){
                    sum+=num.value;
                    numWanted--;
                    map.put(num.label,map.getOrDefault(num.label,0)+1);
                }

                // 判断是否足够
                if (numWanted==0) break;
            }
            return sum;
        }
    }

    static class Num implements Comparable<Num>{
        int value;
        int label;

        public Num(int value, int label) {
            this.value = value;
            this.label = label;
        }

        @Override
        public int compareTo(Num o) {
            // 按照value的从大到小排序，若相同按标签排序
            if (this.value==o.value){
                return this.label-o.label;
            }
            return o.value-this.value;
        }
    }
}
