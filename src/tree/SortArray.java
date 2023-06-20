package tree;

import java.util.Random;

/**
 * 912. 排序数组[medium]
 * https://leetcode.cn/problems/sort-an-array/
 */
public class SortArray {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] sortArray(int[] nums) {
//            Arrays.sort(nums);  // 底层用的是快排
//            return nums;

            // 为了避免出现耗时的极端情况，先随机打乱
            shuffle(nums);
            // 排序整个数组（原地修改）
            sort(nums, 0, nums.length - 1);

            return nums;

        }

        private void sort(int[] nums, int lo, int hi) {
            // 进行判断，如果左边索引比右边大，没有元素
            // 左边索引等于右边，只有一个元素
            // 直接使用return结束这个方法
            if (lo>=hi){
                return;
            }

            // 对 nums[lo..hi] 进行切分
            // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
            int p = partition(nums, lo, hi);
            // 排基准数左边的数
            sort(nums, lo, p - 1);
            // 排基准数右边的数
            sort(nums, p + 1, hi);

        }

        // 对 nums[lo..hi] 进行切分
        private int partition(int[] nums, int lo, int hi) {
            // 基准数
            int base=nums[lo];

            int left=lo,right=hi;
            // 不相遇的时候在循环中进行检索
            while (left<right){
                //先由j从右往左进行检索，检索到比基准数小的就停下
                //如果检索到比基准数大或相等的，就继续检索
                while (left<right && nums[right]>=base){
                    right--;
                }
                //先由i从左往右进行检索，检索到比基准数大的就停下
                //如果检索到比基准数小或相等的，就继续检索
                while (left<right && nums[left]<=base){
                    left++;
                }
                //i，j均停下，i，j位置的元素互换位置
                swap(nums,left,right);
            }

            //当left=right相遇了，就交换基准数和相遇位置的元素
            //把相遇位置的数赋值给基准数位置的元素
            nums[lo]=nums[left];
            nums[left]=base;
            //此时基准数在相遇位置，已归位，左边的数均比它小，右边的数均比它大
            return left;
        }

        // 随机打乱数组
        private void shuffle(int[] nums) {
            Random random = new Random();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 生成[i,n-1]的随机数   random.nextInt(n - i) [0,n-i)
                int r = i + random.nextInt(n - i);
                // 交换i r下标元素
                swap(nums,i,r);
            }
        }

        // 交换i j下标元素
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i]=nums[j];
            nums[j]=tmp;
        }
    }
}
