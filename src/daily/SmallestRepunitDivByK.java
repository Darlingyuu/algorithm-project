package daily;

import java.util.HashSet;
import java.util.Set;

/**
 * 1015. 可被 K 整除的最小整数[medium]
 * 20230510
 * https://leetcode.cn/problems/smallest-integer-divisible-by-k/
 */
public class SmallestRepunitDivByK {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.smallestRepunitDivByK(1));
//        System.out.println(solution.smallestRepunitDivByK(2));
//        System.out.println(solution.smallestRepunitDivByK(3));
        System.out.println(solution.smallestRepunitDivByK(17));
    }

    static class Solution {
        public int smallestRepunitDivByK(int k) {
            // 从1*1=1 3*7=21 9*9=81
            // 若 k 能被 2 或 5 整除，则无解，返回 -1
            if (k % 2 == 0 || k % 5 == 0) {
                return -1;
            }

            //用来存储余数，若出现相同余数，说明陷入循环，无法无法整除
            HashSet<Integer> set = new HashSet<>();
            // 初始化余数和长度
            int res=1%k,length=1;
            set.add(res); // 插入余数1
            while (res!=0){
                //不要记录每次的num,会出现溢出，只需要记录最后即可
                int newNum = res * 10 + 1;
                res=newNum%k;
                if (!set.add(res)){
                    return -1;//出现之前的余数，说明进入循环了，无法整除
                }
                length++;
            }
            return length;

        }

        //官方
        public int smallestRepunitDivByKII(int k) {
            // 若 k 能被 2 或 5 整除，则无解，返回 -1
            if (k % 2 == 0 || k % 5 == 0) {
                return -1;
            }
            // 初始化余数为 1，表示一个数的最低位是 1
            int resid = 1 % k, len = 1;
            // 若余数不为 0，继续迭代
            while (resid != 0) {
                // 计算下一个数的余数，下一个数在当前余数后加一个 1
                resid = (resid * 10 + 1) % k;
                len++;
            }
            // 返回数字 1 的最小重复次数
            return len;
        }

        public int smallestRepunitDivByKIII(int k) {
            int resid = 1 % k, len = 1; // resid为余数，len为数字长度，初始值为1
            Set<Integer> set = new HashSet<Integer>(); // 创建一个无序集合，用于存储余数
            set.add(resid); // 插入余数1
            while (resid != 0) { // 当余数为0时退出循环
                resid = (resid * 10 + 1) % k; // 计算下一个余数
                len++; // 数字长度+1
                if (set.contains(resid)) { // 如果余数重复出现，则无解
                    return -1;
                }
                set.add(resid); // 将余数插入集合
            }
            return len; // 返回数字长度
        }
    }
}
