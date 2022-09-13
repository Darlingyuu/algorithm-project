package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/13 20:11
 */
public class Intersect {
    public static void main(String[] args) {
        int[] num1={1,2,2,1};
        int[] num2={2,2};
        Solution solution = new Solution();
        int[] intersect = solution.intersect(num1, num2);
        System.out.println(Arrays.toString(intersect));
    }

    static class Solution {
        /**
         * 先对两个数组进行排序，然后使用两个指针，分别指向两个数组开始的位置
         * 相同加入集合，不同移动位置
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] intersect(int[] nums1, int[] nums2) {
            ArrayList<Integer> list = new ArrayList<>();
            // 排序
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int p=0,q=0;
            while (p<nums1.length && q<nums2.length) {
                // 相同，则指针都向后移一位
                if (nums1[p] == nums2[q]) {
                    list.add(nums1[p]);
                    p++;
                    q++;
                } else if (nums1[p] < nums2[q]) {
                    // 若不同，值小的对应指针向后移动
                    p++;
                } else {
                    q++;
                }
            }
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i]=list.get(i);
            }
            return res;
        }
    }
}
