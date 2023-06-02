package tree;

/**
 * 327. 区间和的个数[hard]
 * https://leetcode.cn/problems/count-of-range-sum/
 */
public class CountRangeSum {
    public static void main(String[] args) {
        int[] nums={-2147483647,0,-2147483647,2147483647};
        Solution solution = new Solution();
        System.out.println(solution.countRangeSum(nums, -564, 3864));
    }

    static class Solution {
        // 排序使用的辅助数组
        private long[] temp;
        // 记录结果
        private int count;
        private int lower;
        private int upper;

        public int countRangeSum(int[] nums, int lower, int upper) {
            int n = nums.length;
            long[] preSum = new long[n + 1];
            for (int i = 0; i < n; i++) {
                preSum[i+1]=preSum[i]+nums[i];  // 避免int溢出
            }

            // 嵌套 for 循环时间复杂度超了
//            int count=0;
//            // num (i ,j]和
//            for (int i = 0; i < n; i++) {
//                for (int j = i+1; j <= n; j++) {
//                    long sum = preSum[j] - preSum[i];
//                    if (lower<=sum&& sum<=upper) count++;
//                }
//            }
//            return count;

            this.lower=lower;
            this.upper=upper;

            // 利用归并排序
            sort(preSum);

            return count;
        }

        private void sort(long[] nums) {
            temp=new long[nums.length];
            sort(nums,0,nums.length-1);
        }

        private void sort(long[] nums, int low, int high) {
            // 单个数组不用排序
            if (low==high) return;

            int mid = low + (high - low) / 2;
            sort(nums, low, mid);
            sort(nums, mid+1, high);
            merge(nums,low,mid,high);
        }

        private void merge(long[] nums, int low, int mid, int high) {
            // 给辅助数组赋值
            for (int i = low; i <= high; i++) {
                temp[i]=nums[i];
            }

            // 维护左闭右开区间 [start, end) 中的元素和 nums[i] 的差在 [lower, upper] 中
            int start=mid+1,end=mid+1;
            for (int i = low; i <= mid ; i++) {
                // 如果 nums[i] 对应的区间是 [start, end)，
                // 那么 nums[i+1] 对应的区间一定会整体右移，类似滑动窗口
                while (start<=high && nums[start]-nums[i]<lower){
                    start++;
                }
                while (end <= high && nums[end] - nums[i] <= upper) {
                    end++;
                }
                count += end - start;
            }

            // 数组双指针技巧，合并两个有序数组
            int i = low, j = mid + 1;
            for (int p = low; p <= high; p++) {
                if (i == mid + 1) {
                    nums[p] = temp[j++];
                } else if (j == high + 1) {
                    nums[p] = temp[i++];
                } else if (temp[i] > temp[j]) {
                    nums[p] = temp[j++];
                } else {
                    nums[p] = temp[i++];
                }
            }
        }
    }
}
