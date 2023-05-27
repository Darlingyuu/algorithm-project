package array;

/**
 * 304. 二维区域和检索 - 矩阵不可变[medium]
 * https://leetcode.cn/problems/range-sum-query-2d-immutable/
 */
public class NumMatrix {
    private int[][] preSum;
    public NumMatrix(int[][] matrix) {
        preSum=new int[matrix.length+1][matrix[0].length+1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                preSum[i+1][j+1]=preSum[i][j+1]+preSum[i+1][j]+matrix[i][j]-preSum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2+1][col2+1]-preSum[row1][col2+1]-preSum[row2+1][col1]+preSum[row1][col1];
    }


    public static void main(String[] args) {
        int[][] matrix={{-4,-5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
    }
}




