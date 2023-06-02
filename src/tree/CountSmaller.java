package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数[hard]
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
 */
public class CountSmaller {
    public static void main(String[] args) {
        int[] nums={5,2,6,1};
        Solution solution = new Solution();
        List<Integer> list = solution.countSmaller(nums);
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
    }

    static class Solution {
        // 归并排序所用的辅助数组
        private Pair[] temp;
        // 记录每个元素后面比自己小的元素个数
        private int[] count;

        public List<Integer> countSmaller(int[] nums) {
            int n = nums.length;
            // 记录元素原始的索引位置，以便在 count 数组中更新结果
            Pair[] arr = new Pair[n];
            for (int i = 0; i < n; i++){
                arr[i] = new Pair(nums[i], i);
            }

            // 先给辅助数组开辟内存空间
            temp = new Pair[n];
            count=new int[n];

            sort(arr,0,n-1);

            // 结果在count中
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : count) {
                list.add(i);
            }
            return list;
        }


        private void sort(Pair[] arr, int left, int right) {
            // 结束
            if (left==right) {
                // 单个元素不用排序
                return;
            }
            int mid = left + (right - left) / 2;
            // 左边排序
            sort(arr, left, mid);
            // 右边排序
            sort(arr, mid+1, right);
            // 合并成一个有序数组
            merge(arr,left,mid,right);
        }

        private void merge(Pair[] arr, int left, int mid, int right) {
            // 先把 nums[lo..hi] 复制到辅助数组中
            // 以便合并后的结果能够直接存入 nums
            for (int i = left; i <= right; i++) {
                temp[i] = arr[i];
            }

            int p=left,q=mid+1; // 检测指针
            for (int i = left; i <= right; i++) {  // i 存放指针
                if (p==mid+1){ // [left,mid]部分已经全部合并完毕，只需要将[mid+1,right]部分加入即可
                    arr[i]=temp[q++];
                }else if (q==right+1){ // [mid+1,right]部分已经全部合并完毕，只需要将[left,mid]部分加入即可
                    arr[i]=temp[p++];
                    // [mid+1,right] 都是比arr[i]小的数
                    count[arr[i].id]+=q-mid-1;
                }else if (temp[p].val>temp[q].val){
                    arr[i]=temp[q++];
                }else {
                    arr[i]=temp[p++];
                    // temp[p].val<=>temp[q].val
                    // [mid+1,q-1] 都是比arr[i]小的数
                    // 更新count
                    count[arr[i].id]+=q-mid-1;
                }
            }
        }
    }

    private static class Pair {
        int val, id;
        Pair(int val, int id) {
            // 记录数组的元素值
            this.val = val;
            // 记录元素在数组中的原始索引
            this.id = id;
        }
    }
}
