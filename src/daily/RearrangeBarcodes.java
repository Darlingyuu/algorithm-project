package daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1054. 距离相等的条形码[medium]
 * https://leetcode.cn/problems/distant-barcodes/
 */
public class RearrangeBarcodes {
    public static void main(String[] args) {
        int[] barcodes={2,1,1};
        Solution solution = new Solution();
        int[] arr = solution.rearrangeBarcodes(barcodes);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {
        public int[] rearrangeBarcodes(int[] barcodes) {
            if (barcodes==null || barcodes.length==1 ||(barcodes.length==2 && barcodes[0]!=barcodes[1])){
                return barcodes;
            }
            //遍历这个频数表，把每个元素的 (剩余数量， 元素值) 二元数组，依次插入最大堆。
            // 这样操作后，堆顶的元素就是剩余数量最多的元素。
            HashMap<Integer, Integer> count = new HashMap<>();
            for (int barcode : barcodes) {
                count.put(barcode,count.getOrDefault(barcode,0)+1);
            }
            PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->b[0]-a[0]);
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                //(剩余数量， 元素值)
                queue.offer(new int[]{entry.getValue(),entry.getKey()});
            }

            int n = barcodes.length;
            int[] res=new int[n];
            for (int i = 0; i < n; i++) {
                //从最大堆中拿出剩余数量最多的而二元数组
                int[] maxCount = queue.poll();
                int cnt=maxCount[0],num=maxCount[1];
                if (i==0 || res[i-1]!=num){
                    //在i位置上放置num，消耗一个
                    res[i]=num;
                    //重新放回最大堆，数量-1
                    if (cnt>1){
                        queue.offer(new int[]{cnt-1,num});
                    }
                }else {
                    //i-1位置放置的是num,所以i位置要放次最多剩余数量的数
                    int[] nxtCount = queue.poll();
                    int nxtCnt=nxtCount[0],nxtNum=nxtCount[1];
                    res[i]=nxtNum;
                    //重新放回最大堆，数量-1
                    if (nxtCnt>1){
                        queue.offer(new int[]{nxtCnt-1,nxtNum});
                    }
                    // 把剩余数量最多的而二元数组再放进最大堆
                    queue.offer(new int[]{cnt,num});
                }

            }
            return res;
        }
    }
}
