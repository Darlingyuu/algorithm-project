package hot100;

/**
 * 189. 轮转数组[medium]
 * https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&envId=top-100-liked
 */
public class Rotate {
    class Solution {
        public void rotate(int[] nums, int k) {
            // k=0不用交换位置
            if (k == 0) {
                return;
            }
            // 定义临时数组
            int[] temp = new int[nums.length+k];
            // 将nums元素放到temp后面，前面留k个空位用于轮转
            for (int i = 0; i < nums.length; i++) {
                temp[k+i] = nums[i];
            }
            // 记录第一个元素位置和最后一个轮转的元素位置
            int p=k,q=temp.length-1;
            // 轮转k次,每次将q位置的元素放到p前面
            for (int i = 1; i <= k; i++) {
                temp[p-1]=temp[q];
                q--;
                p--;
            }

            // 将temp中元素复制回nums
            for (int i = 0; i < nums.length; i++) {
                nums[i] = temp[i];
            }
        }
    }
}
