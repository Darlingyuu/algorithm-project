package daily;

import java.util.HashSet;

/**
 * 1016. 子串能表示从 1 到 N 数字的二进制串[medium]
 * 20230511
 * https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/
 */
public class QueryString {
    public static void main(String[] args) {
        String s="0110";
        int num = 4;
//        String s="1101100110111010100100101101100011111001001001000110100111111100011111000000111011100001100111010101101111111100110110100000101000001111001101010001010010000010100000100001111001111100000000111010010000100010111000011000010111110111011011100010100001010110100001001011110100101111000011111001110101011001110010001000011001111110101000001011110100111111100010111001000101011001110110010000000110110011011111100101010010010100010111001001001101111110101010011001001111101100001010111101100001100100000001101100110011011101001001011001110110111000011111110101000000101000000000011001011111001000011000010100101101100011011100011101010100111011011111001010011001011110011111101110001010111110010101011101101010011101010010100111101010000110101011111011010011000110011100011100101000000010110001101010110111001010101100001010000010100001101001101011110010001101100001110001001011000111100001010010100011101010111110001011010111000100001010010001011001011011000001000110010110000101101001111010001101101111";
//        int num=100;
        Solution solution = new Solution();
//        System.out.println(solution.test());
        System.out.println(solution.queryStringIII(s, num));
    }

    static class Solution {
        public boolean queryString(String s, int n) {
            String string = getByteString(n);
            int length = string.length();
            // 长度为length-2的二进制字符串必定是长度为length-1的二进制字符串的子串

            //长度为length-1的二进制字符串的最小数字
            // 如长度为3,最小数字4=2*2
            // 如长度为4,最小数字8=2*2*2
            int num=1;
            for (int i = 1; i <length-1; i++) {
                num*=2;
            }
            for (int i = num; i <=n ; i++) {
                //判断num到n的二进制字符串是否在s中
                if (!containSubString(s,getByteString(i))){
                    return false;
                }
            }
            return true;
        }

        private String getByteString(int n){
            return Integer.toBinaryString(n);

//            //计算n的二进制
//            StringBuilder stringBuilder = new StringBuilder();
//            if (n==0 || n==1) {
//                stringBuilder.append(n);
//            }else {
//                while (n != 0) {
//                    stringBuilder.append(n % 2);
//                    n = n / 2;
//                }
//            }
//            // 得到n的二进制字符串
//            return stringBuilder.reverse().toString();
        }

        private boolean containSubString(String s ,String subString){
            int i,j;i=j=0;
            while (i<s.length() && j<subString.length()){
                if (s.charAt(i)==subString.charAt(j)){
                    i++;
                    j++;
                }else {
                    i=i+1-j;
                    j=0;
                }
            }

            if (j==subString.length()) {
                return true;
            }else {
                return false;
            }
        }


        // 使用api方法,暴力求解
        public boolean queryStringII(String s, int n) {
            for (int i = 1; i <= n; i++) {
                if (!s.contains(Integer.toBinaryString(i))){
                    return false;
                }
            }
            return true;
        }

        /*
            将s的子串都转成二进制数
            如果数字在 [1,n]内，就保存到一个哈希表中
            如果哈希表的大小最终为 n，就说明 [1,n]的二进制都在 s 里面。

         */
        public boolean queryStringIII(String s, int n) {
            HashSet<Integer> seen = new HashSet();
            char[] array = s.toCharArray();
            for (int i = 0, m = array.length; i < m; ++i) {
                int x = array[i] - '0';
                if (x == 0) continue; // 二进制数从 1 开始
                for (int j = i + 1; x <= n; j++) {
                    seen.add(x);
                    if (j == m) break;
                    x = (x << 1) | (array[j] - '0'); // 子串 [i,j] 的二进制数 ,  | 或运算
                }
            }
            return seen.size() == n;
        }


    }
}
