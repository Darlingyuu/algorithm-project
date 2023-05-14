package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N 皇后[hard]
 * https://leetcode.cn/problems/n-queens/
 */
public class SolveNQueens {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<List<String>> list = solution.solveNQueens(1);
        List<List<String>> list = solution.solveNQueens(4);
        for (List<String> integerList : list) {
            for (String s : integerList) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }

    static class Solution {
        List<List<String>> res=new ArrayList<>();
        public List<List<String>> solveNQueens(int n) {
            //初始化棋盘
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    builder.append(".");
                }
                board.add(builder.toString());
            }
            ArrayList<String> trace = new ArrayList<>();
            backtrack(board,0);
            return res;
        }

        // 路径：board 中小于 row 的那些行都已经成功放置了皇后
        // 选择列表：第 row 行的所有列都是放置皇后的选择
        // 结束条件：row 超过 board 的最后一行
        private void backtrack(List<String> board , int row) {
            //结束条件
            if (board.size()==row){
                res.add(new ArrayList<>(board));
                return;
            }

            //总行数
            int n = board.get(row).length();

            // 遍历第row行上的每一个位置

            for (int col = 0; col < n; col++) {
                // 排除不合法选择
                if(!isValid(board,row,col)) continue;
                //做选择
                StringBuilder builder = new StringBuilder(board.get(row));
                builder.setCharAt(col,'Q');
                board.set(row,builder.toString());
                //进入下一决策,下一行
                backtrack(board,row+1);
                //撤销选择
                builder.setCharAt(col,'.');
                board.set(row,builder.toString());
            }
        }

        // 是否可以在 board[row][col] 放置皇后
        private boolean isValid(List<String> board, int row, int col) {
            //总行数
            int n = board.size();

            //判断当前列是否有Q
            for (int i = 0; i < n; i++) {
                if (i!=row && board.get(i).charAt(col)=='Q') return false;
            }

            //判断左上方向是否有Q
            for (int i = row-1,j=col-1; i >=0&&j>=0; i--,j--) {
                if (board.get(i).charAt(j)=='Q') return false;
            }

            //判断右上方向是否有Q
            for (int i = row-1,j=col+1; i >=0&&j<n; i--,j++) {
                if (board.get(i).charAt(j)=='Q') return false;
            }

            return true;
        }
    }
}
