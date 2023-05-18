package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1073. 负二进制数相加[medium]
 * https://leetcode.cn/problems/adding-two-negabinary-numbers/
 */
public class AddNegabinary {

    public static void main(String[] args) {
//        int[] arr1={1,1,1,1,1};
//        int[] arr2={1,0,1};
        int[] arr1={1};
        int[] arr2={1,0,1};
        Solution solution = new Solution();
        int[] arr = solution.addNegabinary(arr1, arr2);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {
        public int[] addNegabinary(int[] arr1, int[] arr2) {
            int p=arr1.length-1,q=arr2.length-1;

            List<Integer> ans = new ArrayList<Integer>();
            int n=0;
            while (p>=0 || q>=0 || n!=0){
                int m =n;
                if (p>=0){
                    m += arr1[p];
                }
                if (q>=0){
                    m+=arr2[q];
                }

                // (-2)^i+(-2)^i = - (-2)^ (i+1)
                //可以向前抵消一位
                if (m>=2){
                    ans.add(m-2);
                    n=-1;
                }else if (m>=0){
                    ans.add(m);
                    n=0;
                }else {
                    // 当前位不够抵消，向前面借
                    // -(-2)^i =  (-2)^ (i+1) + (-2)^i
                    ans.add(1);
                    n=1;
                }
                p--;
                q--;
            }

            while (ans.get(ans.size()-1)==0 && ans.size()>1){
                ans.remove(ans.size()-1);
            }

            int[] arr = new int[ans.size()];
            for (int i = 0,j=ans.size()-1; j >=0 ; i++,j--) {
                arr[i]=ans.get(j);
            }

            return arr;
        }
    }
}
