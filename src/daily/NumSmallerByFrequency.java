package daily;

import java.util.Arrays;

/**
 * 1170. 比较字符串最小字母出现频次[medium]
 * https://leetcode.cn/problems/compare-strings-by-frequency-of-the-smallest-character/
 */
public class NumSmallerByFrequency {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] numSmallerByFrequency(String[] queries, String[] words) {
            //对于words中每个字符串进行f函数后，其得到结果数组是一定的，可以对这个结果数组排序
            //然后对于每个f(queries[i])进行二分查找其在排序后的结果数组中的位置即可知道words中有多少单词的f结果比其要大
            int n = words.length;
            int[] fres = new int[n];
            for (int i = 0; i < n; i++) {
                fres[i]=f(words[i]);
            }
            // 从小到大排序
            Arrays.sort(fres);

            int m = queries.length;
            int[] ans = new int[m];
            for (int i = 0; i < m; i++) {
                int res = f(queries[i]);
                // 通过二分法找到大于res的最小下标
                int index=binarySearch(fres,res);
                // words中次数大于res的个数有n-index
                ans[i]=n-index;
            }
            return ans;
        }

        // 找到大于res的最小下标 左边界
        private int binarySearch(int[] fres, int res) {
            int left=0,right=fres.length;
            while (left<right){
                int mid=left+(right-left)/2;
                if (fres[mid]<=res){
                    left=mid+1;
                }else {
                    right=mid;
                }
            }
            return left;
        }

        // 统计s中最小字母出现的次数
        private int f(String s) {
            int cnt=0;
            char ch='z';
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c<ch){
                    ch=c;
                    cnt=1;
                }else if (c==ch){
                    cnt++;
                }
            }
            return cnt;
        }


    }
}
