package array;

import java.util.Arrays;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/24 15:36
 * 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] digits = {9};
        Solution solution = new Solution();
        int[] ints = solution.plusOne(digits);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] plusOne(int[] digits) {
            int length = digits.length;
            // 判断最后一位的数字是否为9
            return exectue(digits,0,length-1);
        }

        private int[] exectue(int[] digits,int from ,int to){
            // 判断最后一位的数字是否为9
            // 如果不为9，直接+1返回
            // 若为9 ，则向前一位判断是否为9
            if (digits[to]!=9){
                digits[to]++;
                return digits;
            }else {
                // 当前位为9,当前位则置为0，前一位+1
                digits[to]=0;
                // 递归进行判按断前一位数字
                // 此处需注意数组中首位是9的情况，此时from=0,to=0
                if (from==0 && to==0){
                    // 创建一个长度+1的数组，并使原数组指向新数组
                    int[] tmp=new int[digits.length+1];
                    tmp[0]=1;
                    for (int i = 0; i < digits.length; i++) {
                        tmp[i+1]=digits[i];
                    }
                    return tmp;
                }
                // 向前递归
                return exectue(digits, from, to - 1);
            }
        }

        /**
         * 用于判断当前数字是否为9
         * @param num
         * @return
         */
        private boolean isNine(int num){
            if (num==9) {
                return true;
            }
            return false;
        }





        // 题解版
        public int[] plusOneII(int[] digits) {
            int length = digits.length;
            for (int i = length - 1; i >= 0; i--) {
                if (digits[i] != 9) {
                    //如果数组当前元素不等于9，直接加1
                    //然后直接返回
                    digits[i]++;
                    return digits;
                } else {
                    //如果数组当前元素等于9，那么加1之后
                    //肯定会变为0，我们先让他变为0
                    digits[i] = 0;
                }
            }
            //除非数组中的元素都是9，否则不会走到这一步，
            //如果数组的元素都是9，我们只需要把数组的长度
            //增加1，并且把数组的第一个元素置为1即可
            int temp[] = new int[length + 1];
            temp[0] = 1;
            return temp;
        }
    }

}
