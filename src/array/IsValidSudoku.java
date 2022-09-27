package array;

import java.util.HashSet;

/**
 * @author Darling yu
 * @version 1.0
 * @date 2022/9/27 21:26
 * 有效的数独
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 */
public class IsValidSudoku {
    public static void main(String[] args) {
        char[][] board={{'.','.','4','.','.','.','6','3','.'},
                        {'.','.','.','.','.','.','.','.','.'},
                        {'5','.','.','.','.','.','.','9','.'},
                        {'.','.','.','5','6','.','.','.','.'},
                        {'4','.','3','.','.','.','.','.','1'},
                        {'.','.','.','7','.','.','.','.','.'},
                        {'.','.','.','5','.','.','.','.','.'},
                        {'.','.','.','.','.','.','.','.','.'},
                        {'.','.','.','.','.','.','.','.','.'}};

        Solution solution = new Solution();
        System.out.println(solution.isValidSudokuII(board));

    }

    static class Solution {
        // 用于记录九宫格的状态  默认0   1为有效  -1为无效
        int[][] place = new int[3][3];


        public boolean isValidSudoku(char[][] board) {
            int length = board.length;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    // 若当前位置是数字
                    if (board[i][j] != '.') {
                        // 检查所在行和列是否有同样数字
                        for (int m = 0; m < length; m++) {
                            if ((m != j && board[i][m]!='.' && board[i][m] == board[i][j]) ||
                                    (m != i && board[m][j]!='.' && board[m][j] == board[i][j])) {
                                return false;
                            }
                        }

                        // 检查所在的3*3宫中是否有同样数字
                        if (!checkPlace(board, i, j)){
                            return false;
                        };

                    }
                }
            }
            return true;
        }

        private boolean checkPlace(char[][] board, int i, int j) {
            // 左上角的下标
            int x = i / 3 * 3;
            int y = j / 3 * 3;

            if (place[x / 3][y / 3] == -1) {
                return false;
            } else if (place[x / 3][y / 3] == 1) {
                return true;
            }

            // 检查所在的3*3宫中是否有同样数字
            // place[0.,0]表示第一个九宫格，以此类推
            HashSet<Character> set = new HashSet<>();
            for (int m = 0; m < 3; m++) {
                for (int n = 0; n < 3; n++) {
                    if (board[x + m][y + n] != '.') {
                        if (!set.add(board[x + m][y + n])) {
                            place[x / 3][y / 3] = -1;
                            return false;
                        }
                    }
                }
            }

            place[x / 3][y / 3] = 1;
            return true;

        }


        /**
         * 题解版
         * @param board
         * @return
         */
        public boolean isValidSudokuII(char[][] board) {
            int length = board.length;
            //二维数组line表示的是对应的行中是否有对应的数字，比如line[0][3]
            //表示的是第0行（实际上是第1行，因为数组的下标是从0开始的）是否有数字3
            int line[][] = new int[length][length];
            int column[][] = new int[length][length];
            int cell[][] = new int[length][length];
            for (int i = 0; i < length; ++i)
                for (int j = 0; j < length; ++j) {
                    //如果还没有填数字，直接跳过
                    if (board[i][j] == '.')
                        continue;
                    //num是当前格子的数字
                    int num = board[i][j] - '0' - 1;
                    //k是第几个单元格，9宫格数独横着和竖着都是3个单元格
                    int k = i / 3 * 3 + j / 3;
                    //如果当前数字对应的行和列以及单元格，只要一个由数字，说明冲突了，直接返回false。
                    //举个例子，如果line[i][num]不等于0，说明第i（i从0开始）行有num这个数字。
                    if (line[i][num] != 0 || column[j][num] != 0 || cell[k][num] != 0)
                        return false;
                    //表示第i行有num这个数字，第j列有num这个数字，对应的单元格内也有num这个数字
                    line[i][num] = column[j][num] = cell[k][num] = 1;
                }
            return true;
        }
    }
}
