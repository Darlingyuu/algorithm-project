package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1079. 活字印刷[medium]
 * https://leetcode.cn/problems/letter-tile-possibilities/
 */
public class NumTilePossibilities {
    public static void main(String[] args) {
        SolutionI solution = new SolutionI();
        System.out.println(solution.numTilePossibilities("AAABBC"));
    }

    static class Solution {
        List<List<Character>> res=new ArrayList<>();
        LinkedList<Character> trace=new LinkedList<>();
        boolean[] visited;
        public int numTilePossibilities(String tiles) {
            char[] array = tiles.toCharArray();
            Arrays.sort(array);
            visited=new boolean[array.length];
            backtrack(array);
            return res.size();
        }

        private void backtrack(char[] array) {
            // 结束条件
            if (trace.size()==array.length) return;
            for (int i = 0; i < array.length; i++) {
                if (visited[i] || (i!=0 && !visited[i-1] && array[i]==array[i-1])){
                    continue;
                }
                //做出选择
                trace.add(array[i]);
                visited[i]=true;
                res.add(new ArrayList<>(trace));
                //下一层决策树
                backtrack(array);
                //撤销选择
                trace.removeLast();
                visited[i]=false;
            }
        }
    }

    static class SolutionI {
        int count=0;
        LinkedList<Character> trace=new LinkedList<>();
        boolean[] visited;
        public int numTilePossibilities(String tiles) {
            char[] array = tiles.toCharArray();
            Arrays.sort(array);
            visited=new boolean[array.length];
            backtrack(array);
            return count;
        }

        private void backtrack(char[] array) {
            // 结束条件
            if (trace.size()==array.length) return;
            for (int i = 0; i < array.length; i++) {
                if (visited[i] || (i!=0 && !visited[i-1] && array[i]==array[i-1])){
                    continue;
                }
                //做出选择
                trace.add(array[i]);
                visited[i]=true;
                count++;
                //下一层决策树
                backtrack(array);
                //撤销选择
                trace.removeLast();
                visited[i]=false;
            }
        }
    }
}
